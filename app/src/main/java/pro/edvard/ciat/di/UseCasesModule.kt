package pro.edvard.ciat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pro.edvard.ciat.business.usecases.GetUsers
import pro.edvard.ciat.business.usecases.RefreshUsers
import pro.edvard.ciat.framework.data.cache.abstraction.UserDaoService
import pro.edvard.ciat.framework.data.cache.abstraction.UserRemoteKeyDaoService
import pro.edvard.ciat.framework.data.network.abstraction.ReqresService
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UseCasesModule {

    @Singleton
    @Provides
    fun provideGetUsers(
        userDaoService: UserDaoService,
        reqresService: ReqresService,
        userRemoteKeyDaoService: UserRemoteKeyDaoService
    ): GetUsers {
        return GetUsers(userDaoService, reqresService, userRemoteKeyDaoService)
    }

    @Singleton
    @Provides
    fun provideRefreshUsers(
        userDaoService: UserDaoService,
        userRemoteKeyDaoService: UserRemoteKeyDaoService
    ): RefreshUsers {
        return RefreshUsers(userDaoService, userRemoteKeyDaoService)
    }

}