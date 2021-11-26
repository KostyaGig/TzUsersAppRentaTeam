package com.ketodiet.plan.com.tzusersapprentateam.presentation.state

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract

interface UiUserToUiStateUserMapper : Abstract.UserMapper<UiUserState> {

    class Base : UiUserToUiStateUserMapper {

        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): UiUserState
            = UiUserState.Base(
                id, email, firstName, lastName, avatar
            )

    }
}