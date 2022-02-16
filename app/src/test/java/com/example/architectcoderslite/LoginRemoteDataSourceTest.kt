package com.example.architectcoderslite

import com.example.architectcoderslite.data.LogInRemoteDataSource
import com.example.architectcoderslite.data.LogInRemoteDataSourceImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test


class LoginRemoteDataSourceTest {

    //First create an instance of LoginRemoteDataSource for test
    private val datasource = LogInRemoteDataSourceImpl()


    @Test
    fun `User without @ returns error`() = runBlocking {
        val result = datasource.tryLogin("agus","")

        //Assert.assertEquals(true, result.userError)          first value espected, two the real value
    }


    @Test
    fun `User with @ returns success`() = runBlocking {
        val result = datasource.tryLogin("agus@", "")
        Assert.assertFalse(result.userError)
    }

    @Test
    fun `pass with characters lesser than 5 return error`(): Unit = runBlocking {
        val result = datasource.tryLogin("", "1234")
        Assert.assertTrue(result.passError)
    }

    @Test
    fun `pass with characters greater than 6 return success`(): Unit = runBlocking {
        val result = datasource.tryLogin("","123456")
        Assert.assertFalse(result.passError)
    }

}