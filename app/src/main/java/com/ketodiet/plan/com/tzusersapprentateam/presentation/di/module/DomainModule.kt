package com.ketodiet.plan.com.tzusersapprentateam.presentation.di.module

import com.ketodiet.plan.com.tzusersapprentateam.data.DataUsers
import com.ketodiet.plan.com.tzusersapprentateam.data.UsersRepository
import com.ketodiet.plan.com.tzusersapprentateam.domain.DataToDomainUserMapper
import com.ketodiet.plan.com.tzusersapprentateam.domain.DataToDomainUsersMapper
import com.ketodiet.plan.com.tzusersapprentateam.domain.UsersInteractor
import dagger.Module
import dagger.Provides
import io.reactivex.Single

@Module
class DomainModule {

    @Provides
    fun provideUsersInteractor(
        usersRepository: UsersRepository<Single<DataUsers>>
    ) : UsersInteractor {
        return UsersInteractor.Base(
            usersRepository,
            DataToDomainUsersMapper.Base(
                DataToDomainUserMapper.Base()
            )
        )
    }
}