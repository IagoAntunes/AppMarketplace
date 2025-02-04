package com.iagoaf.appmarketplace.src.auth.login.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.iagoaf.appmarketplace.core.result.onError
import com.iagoaf.appmarketplace.core.result.onSuccess
import com.iagoaf.appmarketplace.core.routes.AppMarketplaceRoutes
import com.iagoaf.appmarketplace.src.auth.login.presentation.LoginActions
import com.iagoaf.appmarketplace.src.auth.login.presentation.LoginScreenListener
import com.iagoaf.appmarketplace.src.auth.login.presentation.LoginScreenState
import com.iagoaf.appmarketplace.src.auth.register.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    val authRepository: IAuthRepository
) : ViewModel() {

    lateinit var navController: NavController

    private val _state = MutableStateFlow<LoginScreenState>(LoginScreenState.Idle)
    val state = _state
    private val _listener = MutableStateFlow<LoginScreenListener>(LoginScreenListener.Idle)
    val listener = _listener

    fun onAction(action: LoginActions) {
        when (action) {
            is LoginActions.NavigateToRegister -> {
                navigateToRegister()
            }

            is LoginActions.Login -> {
                loginAction(action.email, action.password)
            }

            is LoginActions.NavigateToHome -> {
                navigateToHome()
            }
        }
    }

    private fun navigateToRegister() {
        navController.navigate(AppMarketplaceRoutes.RegisterScreenRoute.name)
    }

    private fun navigateToHome() {
        navController.navigate(AppMarketplaceRoutes.HomeScreenRoute.name)
    }

    private fun loginAction(email: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginScreenState.Loading
            authRepository.login(email, password)
                .onSuccess {
                    _listener.value = LoginScreenListener.Success("User logged in")
                }
                .onError {
                    _listener.value = LoginScreenListener.Error(it.message!!)
                }
            _state.value = LoginScreenState.Idle
        }
    }


}