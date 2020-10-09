package pro.edvard.ciat.framework.data.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pro.edvard.ciat.framework.data.cache.model.UserRemoteKeys

@Dao
interface UserRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(remoteKey: List<UserRemoteKeys>)

    @Query("SELECT * FROM user_remote_keys WHERE user_id = :userId")
    suspend fun remoteKeysUserId(userId: Int): UserRemoteKeys?

    @Query("DELETE FROM user_remote_keys")
    suspend fun reset()

}