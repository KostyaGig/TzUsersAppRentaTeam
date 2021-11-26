package com.ketodiet.plan.com.tzusersapprentateam.domain

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract
import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser

interface DataToDomainUsersMapper : Abstract.UsersMapper<DomainUsers> {

    class Base(
        private val dataToDomainUserMapper: DataToDomainUserMapper
    ) : DataToDomainUsersMapper {

        override fun map(users: List<BaseUser>): DomainUsers
            = DomainUsers.Success(users.map { dataUser ->
                dataUser.map(dataToDomainUserMapper)
        })

        override fun map(message: String): DomainUsers
            = DomainUsers.Failure(message)
    }
}