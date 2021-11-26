package com.ketodiet.plan.com.tzusersapprentateam.data.cache

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract

interface CloudToCacheUserMapper : Abstract.UserMapper<CacheUser> {

    class Base : CloudToCacheUserMapper {

        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): CacheUser
            = CacheUser.Base(
                id, email, firstName, lastName, avatar
            )

    }
}