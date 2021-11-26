package com.ketodiet.plan.com.tzusersapprentateam.core


/**
 * Test for [com.ketodiet.plan.com.tzusersapprentateam.core.Abstract.UsersMapper]
 */

import org.junit.Assert.*
import org.junit.Test

class UsersMapperTest {

    @Test
    fun test_success_map_data_to_domain_users() {
        val mapperDataToDomainUsers = TestDataToDomainUsersMapper()
        val testBaseUsers = listOf(
            TestBaseUser(
                2, "second@mail.ru", "Kostya", "Frake", "kostya_frake.jpg"
            ),
            TestBaseUser(
                4, "fourth@mail.ru", "Big", "Ben", "big_ben.jpg"
            )
        )
        val dataUsers = TestDataUsers.Success(
            testBaseUsers
        )
        val expected = TestDomainUsers.Success(
            listOf(
                TestBaseUser(
                    2, "second@mail.ru", "Kostya", "Frake", "kostya_frake.jpg"
                ),
                TestBaseUser(
                    4, "fourth@mail.ru", "Big", "Ben", "big_ben.jpg"
                )
            )
        )
        val domainUser = dataUsers.map(mapperDataToDomainUsers)
        assertEquals(expected,domainUser)
        assertTrue(domainUser is TestDomainUsers.Success)
    }

    @Test
    fun test_failure_map_data_to_domain_users() {
        val mapperDataToDomainUsers = TestDataToDomainUsersMapper()
        val dataUsers = TestDataUsers.Failure("No connection")
        val expected = TestDomainUsers.Failure("No connection")
        val domainUser = dataUsers.map(mapperDataToDomainUsers)
        assertEquals(expected,domainUser)
        assertTrue(domainUser is TestDomainUsers.Failure)
    }

    private sealed class TestDataUsers : Abstract.Users {

        class Success(
            private val users: List<BaseUser>
        ) : TestDataUsers() {

            override fun <T> map(mapper: Abstract.UsersMapper<T>): T
                = mapper.map(users)
        }

        class Failure(
            private val message: String
        ) : TestDataUsers() {

            override fun <T> map(mapper: Abstract.UsersMapper<T>): T
            = mapper.map(message)
        }
    }

    private sealed class TestDomainUsers : Abstract.Users {

        data class Success(
            private val users: List<BaseUser>
        ) : TestDomainUsers() {

            override fun <T> map(mapper: Abstract.UsersMapper<T>): T
                    = mapper.map(users)
        }

        data class Failure(
            private val message: String
        ) : TestDomainUsers() {

            override fun <T> map(mapper: Abstract.UsersMapper<T>): T
                    = mapper.map(message)
        }
    }

    private class TestDataToDomainUsersMapper : Abstract.UsersMapper<TestDomainUsers> {

        override fun map(users: List<BaseUser>): TestDomainUsers
            = TestDomainUsers.Success(users)

        override fun map(message: String): TestDomainUsers
            = TestDomainUsers.Failure(message)
    }

    private data class TestBaseUser(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : BaseUser {

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
                = mapper.map(id, email, firstName, lastName, avatar)
    }
}