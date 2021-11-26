package com.ketodiet.plan.com.tzusersapprentateam.presentation.di.module

import android.content.Context
import androidx.room.Room
import com.ketodiet.plan.com.tzusersapprentateam.data.cache.*
import dagger.Module
import dagger.Provides
import io.reactivex.Single

@Module
class CacheModule {

    private companion object {
        private const val DATABASE_NAME = "users.db"
    }

    @Provides
    fun provideRoomDatabase(context: Context) : UsersDatabase {
        return Room
                .databaseBuilder(context,UsersDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
    }

    @Provides
    fun provideUsersDao(usersDatabase: UsersDatabase) : UsersDao {
        return usersDatabase.dao()
    }

    @Provides
    fun provideCacheDataSource(usersDao: UsersDao) : CacheDataSource<Single<List<CacheUser>>> {
        return CacheDataSource.Base(
            usersDao,
            CloudToCacheUserMapper.Base()
        )
    }
}