package pro.edvard.ciat.framework.data.cache.abstraction

import androidx.paging.PagingSource
import pro.edvard.ciat.business.domain.model.User

interface UserDaoService {

    suspend fun saveAll(users: List<User>)

    fun findAll(): PagingSource<Int, User>

    suspend fun save(user: User): Long

    suspend fun findById(id: Int): User?

    suspend fun count(): Int

    suspend fun reset()

}