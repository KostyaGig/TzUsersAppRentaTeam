package com.ketodiet.plan.com.tzusersapprentateam.data

/**
 * Test for [com.ketodiet.plan.com.tzusersapprentateam.data.UsersRepository.Test]
 */

import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.CloudDataSource
import com.ketodiet.plan.com.tzusersapprentateam.data.cloud.CloudUser
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class UsersRepositoryTest {

    private lateinit var repository: UsersRepository<DataUsers>

    @Before
    fun setUp() {
        repository = UsersRepository.Test(
            CloudDataSource.Test()
        )
    }

    @Test
    fun test_success_fetching_data() {
        val expected = DataUsers.Success(
            listOf(
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
        )
        val actual = repository.users()

        assertEquals(expected, actual)
        assertTrue(actual is DataUsers.Success)
    }

    @Test
    fun test_failure_fetching_data() {
        val expected = DataUsers.Failure("No connection")
        repository.users()
        val actual = repository.users()

        assertEquals(expected, actual)
        assertTrue(actual is DataUsers.Failure)
    }

}