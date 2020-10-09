package pro.edvard.ciat.framework.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pro.edvard.ciat.business.domain.model.User
import pro.edvard.ciat.framework.data.cache.model.UserRemoteKeys


@Database(entities = [User::class, UserRemoteKeys::class], version = 1)
abstract class CiatDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun userRemoteKeyDao(): UserRemoteKeyDao

    companion object {
        const val DATABASE_NAME = "ciat_db"
    }

}