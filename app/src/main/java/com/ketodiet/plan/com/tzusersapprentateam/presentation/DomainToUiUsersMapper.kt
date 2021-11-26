package com.ketodiet.plan.com.tzusersapprentateam.presentation

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract
import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser

interface DomainToUiUsersMapper : Abstract.UsersMapper<UiUsers> {

    class Base : DomainToUiUsersMapper {

        override fun map(users: List<BaseUser>): UiUsers
            = UiUsers.Success(users)

        override fun map(message: String): UiUsers
            = UiUsers.Failure(message)
    }
}