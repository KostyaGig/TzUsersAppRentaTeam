package com.ketodiet.plan.com.tzusersapprentateam.data.cloud

import io.reactivex.Single
import retrofit2.http.GET

/**
 * Base url - https://reqres.in/api/
 * */

interface UsersService {

    @GET("users")
    fun users() : Single<CloudUsers.Base>
}