package pro.edvard.ciat.business.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import pro.edvard.ciat.business.domain.model.User
import pro.edvard.ciat.framework.data.cache.abstraction.UserDaoService
import pro.edvard.ciat.framework.data.cache.abstraction.UserRemoteKeyDaoService
import pro.edvard.ciat.framework.data.network.abstraction.ReqresService

class GetUsers(
    private val userDaoService: UserDaoService,
    private val reqresService: ReqresService,
    private val userRemoteKeyDaoService: UserRemoteKeyDaoService
) {

    fun execute(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 6,
                prefetchDistance = 6,
                enablePlaceholders = false
            ),
            remoteMediator = UserRemoteMediator(
                userDaoService,
                reqresService,
                userRemoteKeyDaoService
            ),
            pagingSourceFactory = { userDaoService.findAll() }
        ).flow
    }
}