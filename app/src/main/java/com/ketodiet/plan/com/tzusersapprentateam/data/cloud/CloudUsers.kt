package com.ketodiet.plan.com.tzusersapprentateam.data.cloud

import com.google.gson.annotations.SerializedName

interface CloudUsers {

    fun users() : List<CloudUser>

    class Base(
        @SerializedName("data")
        private val users: List<CloudUser.Base>
    ) : CloudUsers {

        override fun users(): List<CloudUser> {
            return users
        }
    }

}