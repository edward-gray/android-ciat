package pro.edvard.ciat.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pro.edvard.ciat.framework.data.network.abstraction.ReqresApi
import pro.edvard.ciat.framework.data.network.abstraction.ReqresService
import pro.edvard.ciat.framework.data.network.implementation.ReqresServiceImpl
import pro.edvard.ciat.framework.data.network.util.NetworkResponseAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonBuilder() : Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideReqresApiRetrofitBuilder(gson: Gson) : Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ReqresApi.BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideReqresApi(retrofitBuilder : Retrofit.Builder) : ReqresApi {
        return retrofitBuilder
            .build()
            .create(ReqresApi::class.java)
    }

    @Singleton
    @Provides
    fun provideReqresService(reqresApi: ReqresApi) : ReqresService {
        return ReqresServiceImpl(reqresApi)
    }


}