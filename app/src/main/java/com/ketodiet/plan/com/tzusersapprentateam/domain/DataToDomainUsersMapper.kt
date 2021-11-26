package com.ketodiet.plan.com.tzusersapprentateam.domain

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract
import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser

interface DataToDomainUsersMapper : Abstract.UsersMapper<DomainUsers> {

    class Base : DataToDomainUsersMapper {

        override fun map(users: List<BaseUser>): DomainUsers
            = DomainUsers.Success(users)

        override fun map(message: String): DomainUsers
            = DomainUsers.Failure(message)
    }
}