package com.iagoaf.appmarketplace.src.auth.login.presentation

sealed class LoginActions {
    object NavigateToRegister : LoginActions()
    object NavigateToHome : LoginActions()
    data class Login(val email: String, val password: String) : LoginActions()
}