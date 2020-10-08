package pro.edvard.ciat.framework.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pro.edvard.ciat.framework.data.cache.model.UserCacheEntity


@Database(entities = [UserCacheEntity::class], version = 1)
abstract class CiatDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "ciat_db"
    }

}