package com.example.architectcoderslite.data

import com.example.architectcoderslite.R
import com.example.architectcoderslite.domain.TryLoginUseCase
import com.example.architectcoderslite.ui.MainViewModel
import kotlinx.coroutines.delay


class LogInRepository(private val logInRemoteDataSource: LogInRemoteDataSource = LogInRemoteDataSourceImpl()) {

    suspend fun tryLogin(username: String, password: String): LogInResult =
        logInRemoteDataSource.tryLogin(username = username, password = password)

}

val LogInResult.success get() = !userError && !passError