package com.ketodiet.plan.com.tzusersapprentateam.presentation.state

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract

interface UiUserToUiCacheStateUser : Abstract.UserMapper<UiUserState> {

    class Base : UiUserToUiCacheStateUser {

        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): UiUserState
            = UiUserState.Cache(
                id, email, firstName, lastName, avatar
            )

    }
}