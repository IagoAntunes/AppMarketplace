package com.iagoaf.appmarketplace.src.auth.register.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.iagoaf.appmarketplace.core.result.onError
import com.iagoaf.appmarketplace.core.result.onSuccess
import com.iagoaf.appmarketplace.src.auth.register.RegisterActions
import com.iagoaf.appmarketplace.src.auth.register.domain.models.UserModel
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
                register(
                    action.name,
                    action.phone,
                    action.mail,
                    action.password
                )
            }

            RegisterActions.ResetListener -> {
                resetListener()
            }
        }
    }

    private fun goBackToLogin() {
        navController.popBackStack()
    }

    private fun register(
        name: String,
        phone: String,
        mail: String,
        password: String
    ) {
        viewModelScope.launch {
            _state.value = RegisterScreenState.Loading
            val userModel = UserModel(
                name = name,
                phone = phone,
                mail = mail,
                password = password
            )
            authRepository.register(userModel)
                .onSuccess { info ->
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
        _state.value = RegisterScreenState.Idle
    }

}