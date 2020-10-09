package pro.edvard.ciat.framework.presenter.userlist

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import pro.edvard.ciat.business.domain.model.User
import pro.edvard.ciat.business.usecases.GetUsers

class UserListViewModel
@ViewModelInject
constructor(
    private val getUsers: GetUsers,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val users: Flow<PagingData<User>> = getUsers.execute().cachedIn(viewModelScope)

}