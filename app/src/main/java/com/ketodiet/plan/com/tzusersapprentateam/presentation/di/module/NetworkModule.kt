package com.ketodiet.plan.com.tzusersapprentateam.presentation.di.module

import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.CloudDataSource
import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.CloudUser
import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.UsersService
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    fun provideGson() = GsonConverterFactory.create()

    private companion object {
        private const val BASE_URL = "https://reqres.in/api/"
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient,gsonConverterFactory: GsonConverterFactory) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideCloudUserService(retrofit: Retrofit) : UsersService {
        return retrofit.create(UsersService::class.java)
    }

    @Provides
    fun provideBaseCloudDataSource(service: UsersService) : CloudDataSource<Single<List<CloudUser>>> {
        return CloudDataSource.Base(service)
    }
}