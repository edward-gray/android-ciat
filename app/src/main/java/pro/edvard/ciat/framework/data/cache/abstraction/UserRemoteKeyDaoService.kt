package pro.edvard.ciat.framework.data.cache.abstraction

import pro.edvard.ciat.framework.data.cache.model.UserRemoteKeys

interface UserRemoteKeyDaoService {

    suspend fun saveAll(remoteKey: List<UserRemoteKeys>)

    suspend fun remoteKeysUserId(userId: Int): UserRemoteKeys?

    suspend fun reset()

}