package pro.edvard.ciat.framework.data.cache.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pro.edvard.ciat.framework.data.cache.model.UserCacheEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(userCacheEntityList: List<UserCacheEntity>)

    @Query("SELECT * FROM users")
    fun findAll(): PagingSource<Int, UserCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(userCacheEntity: UserCacheEntity): Long

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun findById(id: Int): UserCacheEntity?

    @Query("SELECT COUNT(*) FROM users")
    suspend fun count(): Int

    @Query("DELETE FROM users")
    suspend fun reset()

}