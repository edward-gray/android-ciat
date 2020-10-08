package pro.edvard.ciat.framework.presenter.userlist

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pro.edvard.ciat.business.usecases.GetUsers
import pro.edvard.ciat.framework.data.network.model.ReqresNetwork

class UserListViewModel
@ViewModelInject
constructor(
    private val getUsers: GetUsers,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _reqres: MutableLiveData<ReqresNetwork> = MutableLiveData()

    val reqres: LiveData<ReqresNetwork>
        get() = _reqres

    fun getReqres() {
        viewModelScope.launch {
            getUsers.execute().onEach {
                _reqres.value = it
            }.launchIn(viewModelScope)
        }
    }

}