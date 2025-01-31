package com.iagoaf.appmarketplace.src.auth.register.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.iagoaf.appmarketplace.core.result.onError
import com.iagoaf.appmarketplace.core.result.onSuccess
import com.iagoaf.appmarketplace.src.auth.register.RegisterActions
import com.iagoaf.appmarketplace.src.auth.register.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    val authRepository: IAuthRepository
) : ViewModel() {

    lateinit var navController: NavController

    private val _state = MutableStateFlow<RegisterScreenState>(RegisterScreenState.Idle)
    val state = _state

    private val _listener = MutableStateFlow<RegisterScreenListener>(RegisterScreenListener.Idle)
    val listener = _listener

    fun onAction(action: RegisterActions) {
        when (action) {
            is RegisterActions.GoBackToLogin -> {
                goBackToLogin()
            }

            is RegisterActions.Register -> {
                register(action.email, action.password)
            }

            RegisterActions.ResetListener -> {
                resetListener()
            }
        }
    }

    private fun goBackToLogin() {
        navController.popBackStack()
    }

    private fun register(email: String, password: String) {
        viewModelScope.launch {
            _state.value = RegisterScreenState.Loading
            authRepository.register(email, password)
                .onSuccess { info ->
                    Log.d("RegisterViewModel", "User registered: $info")
                    _state.value = RegisterScreenState.Idle
                    _listener.value = RegisterScreenListener.SuccessRegister
                }
                .onError { it ->
                    Log.d("RegisterViewModel", "Register Error: $it")
                    _state.value = RegisterScreenState.Idle
                    _listener.value = RegisterScreenListener.ErrorRegister(it.message!!)
                }
        }
    }

    private fun resetListener() {
        _listener.value = RegisterScreenListener.Idle
    }

}