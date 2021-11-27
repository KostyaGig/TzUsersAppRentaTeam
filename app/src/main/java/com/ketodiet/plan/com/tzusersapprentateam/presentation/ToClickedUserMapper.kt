package com.ketodiet.plan.com.tzusersapprentateam.presentation

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract

interface ToClickedUserMapper : Abstract.UserMapper<ClickedUser> {

    class Base : ToClickedUserMapper {

        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): ClickedUser
            = ClickedUser.Base(
                id, email, firstName, lastName, avatar
            )

    }
}