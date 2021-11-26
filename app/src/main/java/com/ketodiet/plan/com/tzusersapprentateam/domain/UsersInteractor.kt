package com.ketodiet.plan.com.tzusersapprentateam.domain

import com.ketodiet.plan.com.tzusersapprentateam.data.DataUsers
import com.ketodiet.plan.com.tzusersapprentateam.data.UsersRepository
import io.reactivex.Single

interface UsersInteractor {

    fun users() : Single<DomainUsers>

    class Base(
        private val repository: UsersRepository<Single<DataUsers>>,
        private val dataToDomainUsersMapper: DataToDomainUsersMapper
    ) : UsersInteractor {

        override fun users(): Single<DomainUsers> {
            val dataUsers = repository.users()
            return dataUsers.flatMap { dataUsers ->
                Single.just(dataUsers.map(dataToDomainUsersMapper))
            }
        }
    }
}