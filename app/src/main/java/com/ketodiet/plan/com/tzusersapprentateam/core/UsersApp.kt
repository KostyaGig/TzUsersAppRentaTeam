package com.ketodiet.plan.com.tzusersapprentateam.core

import android.app.Application
import com.ketodiet.plan.com.tzusersapprentateam.BuildConfig
import com.ketodiet.plan.com.tzusersapprentateam.presentation.di.component.AppComponent
import com.ketodiet.plan.com.tzusersapprentateam.presentation.di.component.DaggerAppComponent
import com.ketodiet.plan.com.tzusersapprentateam.presentation.di.module.AppModule
import timber.log.Timber

class UsersApp : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        component = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}