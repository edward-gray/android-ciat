package pro.edvard.ciat.framework.data.cache.abstraction

import androidx.paging.PagingSource
import pro.edvard.ciat.framework.data.cache.model.UserCacheEntity

interface UserDaoService {

    suspend fun findAll(): PagingSource<Int, UserCacheEntity>

    suspend fun save(userCacheEntity: UserCacheEntity): Long

    suspend fun findById(id: Int): UserCacheEntity?

    suspend fun reset()

}