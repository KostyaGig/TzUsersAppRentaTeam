package com.ketodiet.plan.com.tzusersapprentateam.data.cache

import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.CloudUser
import io.reactivex.Single

interface CacheDataSource<T> {

    fun users() : T

    fun isNotEmpty() : Boolean

    fun save(users: List<CloudUser>)

    class Base(
        private val dao: UsersDao,
        private val cloudToCacheUserMapper: CloudToCacheUserMapper
    ): CacheDataSource<Single<List<CacheUser>>> {

        override fun users(): Single<List<CacheUser>>
            = dao.users().flatMap {
                Single.just(it as List<CacheUser>)
        }

        override fun save(users: List<CloudUser>) {
            val cacheUsers = users.map { cloudUser ->
                cloudUser.map(cloudToCacheUserMapper)
            }
            dao.insert(cacheUsers.map { it as CacheUser.Base } )
        }

        override fun isNotEmpty(): Boolean
            = dao.probablyEmptyUsers().isNotEmpty()
    }
}