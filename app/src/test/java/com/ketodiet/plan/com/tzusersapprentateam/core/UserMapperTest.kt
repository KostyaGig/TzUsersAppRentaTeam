package com.ketodiet.plan.com.tzusersapprentateam.core

import org.junit.Test

/**
 * Test for [com.ketodiet.plan.com.tzusersapprentateam.core.Abstract.UserMapper]
 */

import org.junit.Assert.*

class UserMapperTest {

    @Test
    fun test_map_data_to_domain_user() {
        val dataToDomainUserMapper = TestDataToDomainUsersMapper()
        val dataUser = TestDataUser(
            1,"first@mail.ru","Uncle","Bob","uncle_bob.jpg"
        )
        val domainUser = dataUser.map(dataToDomainUserMapper)
        val expected = TestDomainUser(
            1,"first@mail.ru","Uncle","Bob","uncle_bob.jpg"
        )
        assertEquals(expected,domainUser)
    }

    @Test
    fun test_map_data_to_domain_users() {
        val dataToDomainUserMapper = TestDataToDomainUsersMapper()
        val dataUsers = listOf(
            TestDataUser(
                1,"first@mail.ru","Uncle","Bob","uncle_bob.jpg"
            ),
            TestDataUser(
                2,"second@mail.ru","Kostya","Frake","kostya_frake.jpg"
            ),
            TestDataUser(
                3,"third@mail.ru","Binos","Bek","binos_bek.jpg"
            )
        )
        val domainUser = dataUsers.map { dataUser ->
            dataUser.map(dataToDomainUserMapper)
        }
        val expected = listOf(
            TestDomainUser(
                1,"first@mail.ru","Uncle","Bob","uncle_bob.jpg"
            ),
            TestDomainUser(
                2,"second@mail.ru","Kostya","Frake","kostya_frake.jpg"
            ),
            TestDomainUser(
                3,"third@mail.ru","Binos","Bek","binos_bek.jpg"
            )
        )
        assertEquals(expected,domainUser)
    }

    private data class TestDataUser(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : BaseUser {

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
            = mapper.map(id, email, firstName, lastName, avatar)
    }

    private data class TestDomainUser(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : BaseUser {

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
            = mapper.map(id, email, firstName, lastName, avatar)
    }

    private inner class TestDataToDomainUsersMapper : Abstract.UserMapper<TestDomainUser> {
        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): TestDomainUser
            = TestDomainUser(id, email, firstName, lastName, avatar)
    }
}