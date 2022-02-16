package com.example.architectcoderslite

import com.example.architectcoderslite.data.LogInRemoteDataSourceImpl
import com.example.architectcoderslite.data.LogInRepository

class LoginRepositoryTest {


    private val ds = LogInRemoteDataSourceImpl()
    private val repository = LogInRepository(ds)
}