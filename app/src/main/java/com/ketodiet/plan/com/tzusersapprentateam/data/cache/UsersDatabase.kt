package com.ketodiet.plan.com.tzusersapprentateam.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CacheUser.Base::class],version = 1,exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun dao() : UsersDao
}