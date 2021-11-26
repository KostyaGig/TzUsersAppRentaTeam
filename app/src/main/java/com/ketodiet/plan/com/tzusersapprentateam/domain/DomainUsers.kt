package com.ketodiet.plan.com.tzusersapprentateam.domain

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract
import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser
import com.ketodiet.plan.com.tzusersapprentateam.data.DataUsers

sealed class DomainUsers : Abstract.Users {

    class Success(
        private val users: List<BaseUser>
    ) : DomainUsers() {

        override fun <T> map(mapper: Abstract.UsersMapper<T>): T
            = mapper.map(users)
    }

    class Cache(
        private val users: List<BaseUser>
    ) : DomainUsers() {

        override fun <T> map(mapper: Abstract.UsersMapper<T>): T
                = mapper.cacheMap(users)
    }

    class Failure(
        private val message: String
    ) : DomainUsers() {

        override fun <T> map(mapper: Abstract.UsersMapper<T>): T
            = mapper.map(message)
    }
}
