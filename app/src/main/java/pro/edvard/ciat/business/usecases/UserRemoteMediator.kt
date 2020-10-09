package pro.edvard.ciat.business.usecases

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import pro.edvard.ciat.business.domain.model.User
import pro.edvard.ciat.business.usecases.UserPagingSource.Companion.REQRES_STARTING_PAGE_INDEX
import pro.edvard.ciat.framework.data.cache.abstraction.UserDaoService
import pro.edvard.ciat.framework.data.cache.abstraction.UserRemoteKeyDaoService
import pro.edvard.ciat.framework.data.cache.database.CiatDatabase
import pro.edvard.ciat.framework.data.cache.mapper.UserCacheMapper
import pro.edvard.ciat.framework.data.cache.model.UserRemoteKeys
import pro.edvard.ciat.framework.data.network.abstraction.ReqresService
import pro.edvard.ciat.framework.data.network.util.NetworkResponse
import java.io.IOException
import java.io.InvalidObjectException

@ExperimentalPagingApi
class UserRemoteMediator(
    private val userDaoService: UserDaoService,
    private val reqresService: ReqresService,
    private val userRemoteKeyDaoService: UserRemoteKeyDaoService,
    private val userCacheMapper: UserCacheMapper
) : RemoteMediator<Int, User>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, User>): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: REQRES_STARTING_PAGE_INDEX
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                        ?: throw InvalidObjectException("Remote key and the prevKey should not be null")

                    val prevKey = remoteKeys.prevKey ?: return MediatorResult.Success(
                        endOfPaginationReached = false
                    )
                    remoteKeys.prevKey
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    if (remoteKeys?.nextKey == null) {
                        throw InvalidObjectException("Remote key should not be null for $loadType")
                    }
                    remoteKeys.nextKey
                }
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

            // checking if users empty
            val endOfPaginationReached = users.isEmpty()

            // saving data to cache
            if (loadType == LoadType.REFRESH) {
                // resetting data in cache
                userRemoteKeyDaoService.reset()
                userDaoService.reset()
            }

            // saving users remote keys to cache
            val prevKey = if (page == REQRES_STARTING_PAGE_INDEX) null else page - 1
            val nextKey = if (endOfPaginationReached) null else page + 1
            val userKeys = users.map {
                UserRemoteKeys(it.id, prevKey, nextKey)
            }
            userRemoteKeyDaoService.saveAll(userKeys)

            // saving users to cache
            val usersAsCacheEntities = users.map {
                userCacheMapper.mapToEntity(it)
            }
            userDaoService.saveAll(usersAsCacheEntities)

            // just return Mediator
            return MediatorResult.Success(
                endOfPaginationReached = endOfPaginationReached
            )

        } catch (e: IOException) {
            return MediatorResult.Error(e)
        }


    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, User>): UserRemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { user ->
            return userRemoteKeyDaoService.remoteKeysUserId(user.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, User>): UserRemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { user ->
            return userRemoteKeyDaoService.remoteKeysUserId(user.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, User>): UserRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { userId ->
                userRemoteKeyDaoService.remoteKeysUserId(userId)
            }
        }
    }

}