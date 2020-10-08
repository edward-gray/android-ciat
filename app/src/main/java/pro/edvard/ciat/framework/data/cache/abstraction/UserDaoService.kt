package pro.edvard.ciat.framework.data.cache.abstraction

import pro.edvard.ciat.framework.data.cache.model.UserCacheEntity

interface UserDaoService {

    suspend fun save(userCacheEntity: UserCacheEntity): Long

    suspend fun findById(id: Int): UserCacheEntity?

    suspend fun reset()

}