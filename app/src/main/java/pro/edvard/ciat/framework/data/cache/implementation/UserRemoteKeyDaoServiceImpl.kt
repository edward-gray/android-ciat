package pro.edvard.ciat.framework.data.cache.implementation

import pro.edvard.ciat.framework.data.cache.abstraction.UserRemoteKeyDaoService
import pro.edvard.ciat.framework.data.cache.database.UserRemoteKeyDao
import pro.edvard.ciat.framework.data.cache.model.UserRemoteKeys

class UserRemoteKeyDaoServiceImpl(
    private val userRemoteKeyDao: UserRemoteKeyDao
): UserRemoteKeyDaoService {

    override suspend fun saveAll(remoteKey: List<UserRemoteKeys>) {
        return userRemoteKeyDao.saveAll(remoteKey)
    }

    override suspend fun remoteKeysUserId(userId: Int): UserRemoteKeys? {
        return userRemoteKeyDao.remoteKeysUserId(userId)
    }

    override suspend fun reset() {
        return userRemoteKeyDao.reset()
    }


}