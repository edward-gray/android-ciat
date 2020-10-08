package pro.edvard.ciat.framework.data.network.abstraction

import pro.edvard.ciat.framework.data.network.model.ReqresNetwork
import pro.edvard.ciat.framework.data.network.model.UserNetwork
import pro.edvard.ciat.framework.data.network.util.NetworkResponse

interface ReqresService {

    suspend fun getUsers(page: Int): NetworkResponse<ReqresNetwork, Error>

    suspend fun getUserById(id: Int): NetworkResponse<UserNetwork, Error>

}