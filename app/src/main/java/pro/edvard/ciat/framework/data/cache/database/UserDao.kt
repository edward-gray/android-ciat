package pro.edvard.ciat.framework.data.cache.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pro.edvard.ciat.business.domain.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(users: List<User>)

    @Query("SELECT * FROM users")
    fun findAll(): PagingSource<Int, User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(user: User): Long

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun findById(id: Int): User?

    @Query("SELECT COUNT(*) FROM users")
    suspend fun count(): Int

    @Query("DELETE FROM users")
    suspend fun reset()

}