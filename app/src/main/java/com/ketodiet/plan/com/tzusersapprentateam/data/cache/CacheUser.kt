package com.ketodiet.plan.com.tzusersapprentateam.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract
import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser

interface CacheUser : BaseUser {

    @Entity(tableName = "users")
    data class Base(
        @ColumnInfo(name = "id")
        var id: Int = 0,
        @ColumnInfo(name = "email")
        @PrimaryKey
        var email: String = "",
        @ColumnInfo(name = "firstName")
        var firstName: String = "",
        @ColumnInfo(name = "lastName")
        var lastName: String = "",
        @ColumnInfo(name = "avatar")
        var avatar: String = ""
    ) : CacheUser {

        constructor() : this(0,"","","","")

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
                = mapper.map(id, email, firstName, lastName, avatar)
    }
}