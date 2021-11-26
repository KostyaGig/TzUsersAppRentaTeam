package com.ketodiet.plan.com.tzusersapprentateam.presentation

import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract
import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser

sealed class UiUsers : Abstract.Users {

    class Success(
        private val users: List<BaseUser>
    ) : UiUsers() {

        override fun <T> map(mapper: Abstract.UsersMapper<T>): T
            = mapper.map(users)
    }

    class Failure(
        private val message: String
    ) : UiUsers() {

        override fun <T> map(mapper: Abstract.UsersMapper<T>): T
            = mapper.map(message)
    }
}
