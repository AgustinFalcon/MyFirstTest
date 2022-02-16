package com.example.architectcoderslite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architectcoderslite.R
import com.example.architectcoderslite.data.LogInRepository
import com.example.architectcoderslite.data.success
import com.example.architectcoderslite.domain.TryLoginUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainViewModel(private val tryLoginUseCase: TryLoginUseCase = TryLoginUseCase()) : ViewModel() {

    private var _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state


    sealed class UiStateOld {
        object Loading : UiStateOld()
        data class Error(val userError: String, val passError: String) : UiStateOld()
        object LoggedIn : UiStateOld()
    }

    data class UiState(
        val loggingIn: Boolean = false,
        val loggedIn: Boolean = false,
        val userError: Int? = null,
        val passError: Int? = null
    )


    fun onTryLogin(user: String, pass: String) {
        viewModelScope.launch {
            _state.value = UiState(loggingIn = true)
            val result = tryLoginUseCase.invoke(user, pass)

            _state.value = UiState(
                userError = if (result.userError) R.string.user_error else null,
                passError = if (result.passError) R.string.pass_error else null,
                loggedIn = result.success
            )
        }
    }




    fun onNavigateToNextScreen(){
        _state.value = requireNotNull(_state.value).copy(loggedIn = false)
    }



}