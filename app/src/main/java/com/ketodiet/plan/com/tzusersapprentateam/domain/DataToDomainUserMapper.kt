package com.ketodiet.plan.com.tzusersapprentateam.domain

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract

interface DataToDomainUserMapper : Abstract.UserMapper<DomainUser> {

    class Base : DataToDomainUserMapper {

        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): DomainUser
            = DomainUser.Base(
                id, email, firstName, lastName, avatar
            )

    }
}