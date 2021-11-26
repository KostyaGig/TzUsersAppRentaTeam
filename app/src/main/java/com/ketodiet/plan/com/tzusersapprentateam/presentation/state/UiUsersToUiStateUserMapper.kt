package com.ketodiet.plan.com.tzusersapprentateam.presentation.state

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract
import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser

interface UiUsersToUiStateUserMapper : Abstract.UsersMapper<List<UiUserState>> {

    class Base(
        private val uiUserToUiStateUserMapper: UiUserToUiStateUserMapper
    ) : UiUsersToUiStateUserMapper {

        override fun map(users: List<BaseUser>): List<UiUserState>
            = users.map { uiUser ->
                uiUser.map(uiUserToUiStateUserMapper)
        }

        override fun map(message: String): List<UiUserState>
            = listOf(
                UiUserState.Failure(message)
            )

    }
}