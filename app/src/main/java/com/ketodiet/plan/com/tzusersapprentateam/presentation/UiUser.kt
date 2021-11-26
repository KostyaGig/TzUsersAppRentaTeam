package com.ketodiet.plan.com.tzusersapprentateam.presentation

import com.google.gson.annotations.SerializedName
import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract
import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser

interface UiUser : BaseUser {

    class Base(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : UiUser {

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
            = mapper.map(id, email, firstName, lastName, avatar)
    }
}