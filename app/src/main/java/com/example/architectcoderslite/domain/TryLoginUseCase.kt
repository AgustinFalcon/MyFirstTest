package com.example.architectcoderslite.domain

import com.example.architectcoderslite.data.LogInRepository
import com.example.architectcoderslite.data.LogInResult



class TryLoginUseCase(private val logInRepository: LogInRepository = LogInRepository()) {

    suspend operator fun invoke(username: String, password: String) : LogInResult =
        logInRepository.tryLogin(username = username, password = password)

}