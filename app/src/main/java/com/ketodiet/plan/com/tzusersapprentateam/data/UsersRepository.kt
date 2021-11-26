package com.ketodiet.plan.com.tzusersapprentateam.data

import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser
import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.CloudDataSource
import io.reactivex.Single

interface UsersRepository<T> {

    fun users() : T

    class Base : UsersRepository<Single<DataUsers>> {
        override fun users(): Single<DataUsers> {
            TODO()
        }

    }

    class Test(
        private val cloudDataSource: CloudDataSource<List<BaseUser>>
    ) : UsersRepository<DataUsers> {

        private var returnFailure = false

        override fun users(): DataUsers {

            return if (returnFailure) {
                DataUsers.Failure("No connection")
            } else {
                val cloudUsers = cloudDataSource.users()
                returnFailure = true
                DataUsers.Success(cloudUsers)
            }
        }

    }
}