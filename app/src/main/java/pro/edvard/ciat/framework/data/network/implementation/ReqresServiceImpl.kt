package pro.edvard.ciat.framework.data.network.implementation

import pro.edvard.ciat.framework.data.network.abstraction.ReqresApi
import pro.edvard.ciat.framework.data.network.abstraction.ReqresService
import pro.edvard.ciat.framework.data.network.model.ReqresNetwork
import pro.edvard.ciat.framework.data.network.model.UserNetwork
import pro.edvard.ciat.framework.data.network.util.NetworkResponse

class ReqresServiceImpl(
    private val reqresApi: ReqresApi
): ReqresService {

    override suspend fun getUsers(page: Int): NetworkResponse<ReqresNetwork, Error> {
        return reqresApi.getUsers(page)
    }

    override suspend fun getUserById(id: Int): NetworkResponse<UserNetwork, Error> {
        return reqresApi.getUserById(id)
    }


}