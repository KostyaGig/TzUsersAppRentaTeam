package com.ketodiet.plan.com.tzusersapprentateam.presentation.state

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract
import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser
import com.ketodiet.plan.com.tzusersapprentateam.presentation.core.log

interface UiUsersToUiStateUserMapper : Abstract.UsersMapper<List<UiUserState>> {

    class Base(
        private val toCommonStateUserMapper: UiUserToUiCommonStateUser,
        private val toCacheStateUserMapper: UiUserToUiCacheStateUser,
    ) : UiUsersToUiStateUserMapper {

        override fun map(users: List<BaseUser>): List<UiUserState> {
            log("State To common model map")
            return users.map { uiUser ->
                uiUser.map(toCommonStateUserMapper)
            }
        }

        override fun cacheMap(users: List<BaseUser>): List<UiUserState> {
            log("State To cache model map")
            return users.map { uiUser ->
                uiUser.map(toCacheStateUserMapper)
            }
        }

        override fun map(message: String): List<UiUserState>
            = listOf(
                UiUserState.Failure(message)
            )

    }
}