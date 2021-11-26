package com.ketodiet.plan.com.tzusersapprentateam.presentation.state

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract

interface UiUserToUiCommonStateUser : Abstract.UserMapper<UiUserState> {

    class Base : UiUserToUiCommonStateUser {

        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): UiUserState
                = UiUserState.Common(
            id, email, firstName, lastName, avatar
        )

    }
}