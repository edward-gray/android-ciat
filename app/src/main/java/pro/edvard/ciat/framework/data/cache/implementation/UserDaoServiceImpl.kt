package pro.edvard.ciat.framework.data.cache.implementation

import pro.edvard.ciat.framework.data.cache.abstraction.UserDaoService
import pro.edvard.ciat.framework.data.cache.database.UserDao
import pro.edvard.ciat.framework.data.cache.model.UserCacheEntity

class UserDaoServiceImpl(
    private val userDao: UserDao
): UserDaoService {

    override suspend fun save(userCacheEntity: UserCacheEntity): Long {
       return userDao.save(userCacheEntity)
    }

    override suspend fun findById(id: Int): UserCacheEntity? {
        return userDao.findById(id)
    }

    override suspend fun reset() {
        userDao.reset()
    }

}