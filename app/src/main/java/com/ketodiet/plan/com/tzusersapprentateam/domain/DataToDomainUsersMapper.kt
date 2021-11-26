package com.ketodiet.plan.com.tzusersapprentateam.domain

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract
import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser
import com.ketodiet.plan.com.tzusersapprentateam.presentation.core.log

interface DataToDomainUsersMapper : Abstract.UsersMapper<DomainUsers> {

    class Base(
        private val dataToDomainUserMapper: DataToDomainUserMapper
    ) : DataToDomainUsersMapper {

        override fun map(users: List<BaseUser>): DomainUsers {
            log("To Domain Success map")
            return DomainUsers.Success(users.map { dataUser ->
                dataUser.map(dataToDomainUserMapper)
            })
        }

        override fun cacheMap(users: List<BaseUser>): DomainUsers {
            log("To Domain Cache map")
            return DomainUsers.Cache(
                users.map { dataUser ->
                    dataUser.map(dataToDomainUserMapper)
                }
            )
        }
        override fun map(message: String): DomainUsers
            = DomainUsers.Failure(message)

    }
}