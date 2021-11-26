package com.ketodiet.plan.com.tzusersapprentateam.presentation

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract

interface DomainToUiUserMapper : Abstract.UserMapper<UiUser> {

    class Base : DomainToUiUserMapper {

        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): UiUser
            = UiUser.Base(
                id, email, firstName, lastName, avatar
            )

    }
}