package com.ketodiet.plan.com.tzusersapprentateam.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<CacheUser.Base>)

    @Query("select * from users")
    fun users() : Single<List<CacheUser.Base>>

    //for checking local storage on empty
    @Query("select * from users")
    fun probablyEmptyUsers() : List<CacheUser.Base>
}