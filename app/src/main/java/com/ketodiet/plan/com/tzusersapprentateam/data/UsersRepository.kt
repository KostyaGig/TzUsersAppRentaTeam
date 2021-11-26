package com.ketodiet.plan.com.tzusersapprentateam.data

import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser
import com.ketodiet.plan.com.tzusersapprentateam.data.cache.CacheDataSource
import com.ketodiet.plan.com.tzusersapprentateam.data.cache.CacheUser
import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.CloudDataSource
import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.CloudUser
import io.reactivex.Single

interface UsersRepository<T> {

    fun users() : T

    class Base(
        private val cloudDataSource: CloudDataSource<Single<List<CloudUser>>>,
        private val cacheDataSource: CacheDataSource<Single<List<CacheUser>>>,
        private val toDataUserMapper: ToDataUserMapper,
        private val exceptionMapper: ExceptionMapper<String>
    ) : UsersRepository<Single<DataUsers>> {

        override fun users(): Single<DataUsers> {
            return if (cacheDataSource.isNotEmpty()) {
                usersFromCache()
            } else {
                val cloudUsers = cloudDataSource.users()
                cloudUsers.flatMap<DataUsers> { users ->
                    cacheDataSource.save(users)
                    val dataUsers = users.map { user ->
                        user.map(toDataUserMapper)
                    }
                    Single.just(DataUsers.Success(dataUsers))
                }.onErrorReturn  { throwable ->
                    val errorMessage = exceptionMapper.map(throwable)
                    DataUsers.Failure(errorMessage)
                }
            }
        }

        private fun usersFromCache() : Single<DataUsers> {
            val cacheUsers = cacheDataSource.users()
            return cacheUsers.flatMap { users ->
                val dataUsers = users.map { cacheUser ->
                    cacheUser.map(toDataUserMapper)
                }
                Single.just(DataUsers.Cache(dataUsers))
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