package com.ketodiet.plan.com.tzusersapprentateam.data

/**
 * Test for [com.ketodiet.plan.com.tzusersapprentateam.data.ExceptionMapper.Test]
 */

import com.ketodiet.plan.com.tzusersapprentateam.core.ResourceProvider
import org.junit.Assert.*
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception
import java.net.UnknownHostException

class ExceptionMapperTest {

    @Test
    fun test_map_exception_to_string() {
        val exceptionMapper = ExceptionMapper.Test(
            ResourceProvider.Test()
        )

        var exception: Exception = UnknownHostException()
        var expected = "No connection"
        var actual = exceptionMapper.map(exception)

        assertEquals(expected, actual)

        exception = HttpException(Response.success(null))
        expected = "Some went wrong"
        actual = exceptionMapper.map(exception)

        assertEquals(expected, actual)
    }
}