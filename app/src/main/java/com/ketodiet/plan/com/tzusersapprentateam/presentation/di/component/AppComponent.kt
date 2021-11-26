package com.ketodiet.plan.com.tzusersapprentateam.presentation.di.component

import com.ketodiet.plan.com.tzusersapprentateam.presentation.core.MainActivity
import com.ketodiet.plan.com.tzusersapprentateam.presentation.di.module.AppModule
import com.ketodiet.plan.com.tzusersapprentateam.presentation.fragment.UsersFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: UsersFragment)
}