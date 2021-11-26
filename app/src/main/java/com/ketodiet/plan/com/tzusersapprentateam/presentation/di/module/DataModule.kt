package com.ketodiet.plan.com.tzusersapprentateam.presentation.di.module

import com.ketodiet.plan.com.tzusersapprentateam.core.ResourceProvider
import com.ketodiet.plan.com.tzusersapprentateam.data.ToDataUserMapper
import com.ketodiet.plan.com.tzusersapprentateam.data.DataUsers
import com.ketodiet.plan.com.tzusersapprentateam.data.ExceptionMapper
import com.ketodiet.plan.com.tzusersapprentateam.data.UsersRepository
import com.ketodiet.plan.com.tzusersapprentateam.data.cache.CacheDataSource
import com.ketodiet.plan.com.tzusersapprentateam.data.cache.CacheUser
import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.CloudDataSource
import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.CloudUser
import dagger.Module
import dagger.Provides
import io.reactivex.Single

@Module(includes = [NetworkModule::class, CacheModule::class])
class DataModule {

    @Provides
    fun provideUsersRepository(
        cacheDataSource: CacheDataSource<Single<List<CacheUser>>>,
        cloudDataSource: CloudDataSource<Single<List<CloudUser>>>,
        resourceProvider: ResourceProvider
    ) : UsersRepository<Single<DataUsers>>  {
        return UsersRepository.Base(
            cloudDataSource,
            cacheDataSource,
            ToDataUserMapper.Base(),
            ExceptionMapper.Base(resourceProvider)
        )
    }
}