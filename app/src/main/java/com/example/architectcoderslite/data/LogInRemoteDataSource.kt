package com.example.architectcoderslite.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface LogInRemoteDataSource {
    suspend fun tryLogin(username: String, password: String): LogInResult
}



class LogInRemoteDataSourceImpl : LogInRemoteDataSource {

    override suspend fun tryLogin(username: String, password: String): LogInResult = withContext(Dispatchers.IO) {
        delay(2000)
        val userError = !username.contains('@')
        val passError = password.length < 5

        LogInResult(userError, passError)

    }
}