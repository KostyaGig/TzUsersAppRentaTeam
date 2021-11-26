package com.ketodiet.plan.com.tzusersapprentateam.presentation

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract
import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser
import com.ketodiet.plan.com.tzusersapprentateam.domain.DomainUsers

interface DomainToUiUsersMapper : Abstract.UsersMapper<UiUsers> {

    class Base(
        private val domainToUiUserMapper: DomainToUiUserMapper
    ) : DomainToUiUsersMapper {

        override fun map(users: List<BaseUser>): UiUsers
            = UiUsers.Success(users.map {domainUser ->
                domainUser.map(domainToUiUserMapper)
        })

        override fun cacheMap(users: List<BaseUser>): UiUsers
                = UiUsers.Cache(
            users.map { domainUser ->
                domainUser.map(domainToUiUserMapper)
            }
        )

        override fun map(message: String): UiUsers
            = UiUsers.Failure(message)
    }
}