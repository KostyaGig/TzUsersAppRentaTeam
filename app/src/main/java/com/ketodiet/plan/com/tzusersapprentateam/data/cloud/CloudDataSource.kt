package com.ketodiet.plan.com.tzusersapprentateam.data.cloud

import com.ketodiet.plan.com.tzusersapprentateam.core.BaseUser
import com.ketodiet.plan.com.tzusersapprentateam.data.cache.CloudToCacheUserMapper
import io.reactivex.Single

interface CloudDataSource<T> {

    fun users() : T

    class Base(
        private val userService: UsersService
    ) : CloudDataSource<Single<List<CloudUser>>> {

        override fun users(): Single<List<CloudUser>> {
            val cloudUsers = userService.users()
            return cloudUsers.flatMap { users ->
                Single.just(users.users())
            }
        }
    }

    class Test : CloudDataSource<List<BaseUser>> {

        override fun users(): List<BaseUser> {
            return listOf(
                CloudUser.Base(
                    1,"first@mail.ru","Uncle","Bob","uncle_bob.jpg"
                ),
                CloudUser.Base(
                    2,"second@mail.ru","Kostya","Frake","kostya_frake.jpg"
                ),
                CloudUser.Base(
                    3,"third@mail.ru","Binos","Bek","binos_bek.jpg"
                )
            )
        }
    }
}