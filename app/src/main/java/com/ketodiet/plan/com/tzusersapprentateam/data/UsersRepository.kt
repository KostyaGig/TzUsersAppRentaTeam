package com.ketodiet.plan.com.tzusersapprentateam.data

import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser
import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.CloudDataSource
import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.CloudUser
import io.reactivex.Single

interface UsersRepository<T> {

    fun users() : T

    class Base(
        private val cloudDataSource: CloudDataSource<Single<List<CloudUser>>>,
        private val cloudToDataUserMapper: CloudToDataUserMapper,
        private val exceptionMapper: ExceptionMapper<String>
    ) : UsersRepository<Single<DataUsers>> {

        override fun users(): Single<DataUsers> {
            return try {
                val cloudUsers = cloudDataSource.users()
                return cloudUsers.flatMap { users ->
                    val dataUsers = users.map { user ->
                        user.map(cloudToDataUserMapper)
                    }
                    Single.just(DataUsers.Success(dataUsers))
                }
            } catch (e: Exception) {
                val errorMessage = exceptionMapper.map(e)
                Single.just(DataUsers.Failure(errorMessage))
            }

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