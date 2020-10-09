package pro.edvard.ciat.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import pro.edvard.ciat.framework.data.cache.abstraction.UserDaoService
import pro.edvard.ciat.framework.data.cache.abstraction.UserRemoteKeyDaoService
import pro.edvard.ciat.framework.data.cache.database.CiatDatabase
import pro.edvard.ciat.framework.data.cache.database.UserDao
import pro.edvard.ciat.framework.data.cache.database.UserRemoteKeyDao
import pro.edvard.ciat.framework.data.cache.implementation.UserDaoServiceImpl
import pro.edvard.ciat.framework.data.cache.implementation.UserRemoteKeyDaoServiceImpl
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object CacheModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context) : CiatDatabase {
        return Room
            .databaseBuilder(app, CiatDatabase::class.java , CiatDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(database : CiatDatabase) : UserDao {
        return database.userDao()
    }

    @Singleton
    @Provides
    fun provideUserDaoService(userDao: UserDao) : UserDaoService {
        return UserDaoServiceImpl(userDao)
    }

    @Singleton
    @Provides
    fun provideUserRemoteKeyDao(database : CiatDatabase) : UserRemoteKeyDao {
        return database.userRemoteKeyDao()
    }

    @Singleton
    @Provides
    fun provideUserRemoteKeyDaoService(userRemoteKeyDao: UserRemoteKeyDao) : UserRemoteKeyDaoService {
        return UserRemoteKeyDaoServiceImpl(userRemoteKeyDao)
    }

}