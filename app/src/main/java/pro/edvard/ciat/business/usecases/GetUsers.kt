package pro.edvard.ciat.business.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pro.edvard.ciat.framework.data.cache.abstraction.UserDaoService
import pro.edvard.ciat.framework.data.cache.mapper.UserCacheMapper
import pro.edvard.ciat.framework.data.network.abstraction.ReqresService
import pro.edvard.ciat.framework.data.network.model.ReqresNetwork
import pro.edvard.ciat.framework.data.network.util.NetworkResponse

class GetUsers(
    private val userCacheMapper: UserCacheMapper,
    private val reqresService: ReqresService,
    private val userDaoService: UserDaoService
) {

    suspend fun execute(): Flow<ReqresNetwork> = flow {

        // get users from api
        reqresService.getUsers(1).let {
            when (it) {
                is NetworkResponse.Success -> {
                    emit(it.body)
                }
            }
        }

    }

}