package pro.edvard.ciat.business.usecases

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import pro.edvard.ciat.business.domain.model.User
import pro.edvard.ciat.framework.data.cache.abstraction.UserDaoService
import pro.edvard.ciat.framework.data.cache.mapper.UserCacheMapper
import pro.edvard.ciat.framework.data.network.abstraction.ReqresService
import pro.edvard.ciat.framework.data.network.util.NetworkResponse
import java.io.IOException

@ExperimentalPagingApi
class UserRemoteMediator(
    private val userDaoService: UserDaoService,
    private val reqresService: ReqresService,
    private val userCacheMapper: UserCacheMapper
) : RemoteMediator<Int, User>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, User>): MediatorResult {

        try {
            val page = when (loadType) {
                LoadType.APPEND -> null
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.REFRESH -> null
                else -> null
            }

            // getting data from network
            val users: List<User> = reqresService.getUsers(page ?: 1).let { response ->
                when (response) {
                    is NetworkResponse.Success -> {
                        val users = response.body.users
                        users
                    }
                    else -> {
                        listOf()
                    }
                }
            }

            // saving data to cache
            users.forEach { user ->
                val userEntity = userCacheMapper.mapToEntity(user)
                userDaoService.save(userEntity)
            }

            val endOfPaginationReached = users.isEmpty()
            return MediatorResult.Success(
                endOfPaginationReached = endOfPaginationReached
            )

        } catch (e: IOException) {
            return MediatorResult.Error(e)
        }


    }

}