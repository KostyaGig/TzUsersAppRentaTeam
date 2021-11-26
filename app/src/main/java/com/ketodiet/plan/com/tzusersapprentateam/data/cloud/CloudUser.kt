package com.ketodiet.plan.com.tzusersapprentateam.data.cloud

import com.google.gson.annotations.SerializedName
import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract
import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser

interface CloudUser : BaseUser {

    data class Base(
        @SerializedName("id")
        private val id: Int = -1,
        @SerializedName("email")
        private val email: String = "",
        @SerializedName("first_name")
        private val firstName: String = "",
        @SerializedName("last_name")
        private val lastName: String = "",
        @SerializedName("avatar")
        private val avatar: String = ""
    ) : CloudUser {

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
            = mapper.map(id, email, firstName, lastName, avatar)
    }
}