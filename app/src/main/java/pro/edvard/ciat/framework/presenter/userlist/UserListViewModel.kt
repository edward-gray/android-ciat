package pro.edvard.ciat.framework.presenter.userlist

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import pro.edvard.ciat.business.domain.model.User
import pro.edvard.ciat.business.usecases.UserPagingSource
import pro.edvard.ciat.business.usecases.UserRemoteMediator
import pro.edvard.ciat.framework.data.cache.abstraction.UserDaoService
import pro.edvard.ciat.framework.data.cache.abstraction.UserRemoteKeyDaoService
import pro.edvard.ciat.framework.data.cache.mapper.UserCacheMapper
import pro.edvard.ciat.framework.data.network.abstraction.ReqresService

@ExperimentalPagingApi
class UserListViewModel
@ViewModelInject
constructor(
    private val userDaoService: UserDaoService,
    private val reqresService: ReqresService,
    private val userCacheMapper: UserCacheMapper,
    private val userRemoteKeyDaoService: UserRemoteKeyDaoService,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val users: Flow<PagingData<User>> = Pager(
        config = PagingConfig(
            pageSize = 6,
            enablePlaceholders = true
        ),
        remoteMediator = UserRemoteMediator(
            userDaoService,
            reqresService,
            userRemoteKeyDaoService,
            userCacheMapper
        ),
        pagingSourceFactory = { UserPagingSource(reqresService) }
    ).flow.cachedIn(viewModelScope)

}