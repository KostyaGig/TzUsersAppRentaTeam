package com.ketodiet.plan.com.tzusersapprentateam.presentation.di.module

import com.ketodiet.plan.com.tzusersapprentateam.core.ResourceProvider
import com.ketodiet.plan.com.tzusersapprentateam.data.CloudToDataUserMapper
import com.ketodiet.plan.com.tzusersapprentateam.data.DataUsers
import com.ketodiet.plan.com.tzusersapprentateam.data.ExceptionMapper
import com.ketodiet.plan.com.tzusersapprentateam.data.UsersRepository
import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.CloudDataSource
import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.CloudUser
import dagger.Module
import dagger.Provides
import io.reactivex.Single

@Module(includes = [NetworkModule::class])
class DataModule {

    @Provides
    fun provideUsersRepository(
        cloudDataSource: CloudDataSource<Single<List<CloudUser>>>,
        resourceProvider: ResourceProvider
    ) : UsersRepository<Single<DataUsers>>  {
        return UsersRepository.Base(
            cloudDataSource,
            CloudToDataUserMapper.Base(),
            ExceptionMapper.Base(resourceProvider)
        )
    }
}