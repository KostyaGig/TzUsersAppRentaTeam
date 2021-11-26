package com.ketodiet.plan.com.tzusersapprentateam.presentation.di.module

import com.ketodiet.plan.com.tzusersapprentateam.domain.UsersInteractor
import com.ketodiet.plan.com.tzusersapprentateam.presentation.UsersViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class UiModule {

    @Provides
    fun provideUsersViewModelFactory(
        usersInteractor: UsersInteractor
    ) : UsersViewModelFactory {
        return UsersViewModelFactory.Base(
            usersInteractor
        )
    }

}