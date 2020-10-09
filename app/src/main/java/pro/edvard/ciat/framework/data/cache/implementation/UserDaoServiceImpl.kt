package pro.edvard.ciat.framework.data.cache.implementation

import androidx.paging.PagingSource
import pro.edvard.ciat.business.domain.model.User
import pro.edvard.ciat.framework.data.cache.abstraction.UserDaoService
import pro.edvard.ciat.framework.data.cache.database.UserDao

class UserDaoServiceImpl(
    private val userDao: UserDao
): UserDaoService {
    override suspend fun saveAll(users: List<User>) {
        return userDao.saveAll(users)
    }

    override fun findAll(): PagingSource<Int, User> {
        return userDao.findAll()
    }

    override suspend fun save(user: User): Long {
        return userDao.save(user)
    }

    override suspend fun findById(id: Int): User? {
        return userDao.findById(id)
    }

    override suspend fun count(): Int {
        return userDao.count()
    }

    override suspend fun reset() {
        return userDao.reset()
    }


}