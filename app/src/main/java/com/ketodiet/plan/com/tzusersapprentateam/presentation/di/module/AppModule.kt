package com.ketodiet.plan.com.tzusersapprentateam.presentation.di.module

import android.content.Context
import com.ketodiet.plan.com.tzusersapprentateam.core.ResourceProvider
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class,DomainModule::class,UiModule::class])
class AppModule(
    private val context: Context
) {

    @Provides
    fun provideContext() = context

    @Provides
    fun provideResourceProvider(context: Context) : ResourceProvider {
        return ResourceProvider.Base(context)
    }
}