package com.ketodiet.plan.com.tzusersapprentateam.data

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract

interface CloudToDataUserMapper : Abstract.UserMapper<DataUser> {

    class Base : CloudToDataUserMapper {

        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): DataUser
            = DataUser.Base(
                id, email, firstName, lastName, avatar
            )

    }
}