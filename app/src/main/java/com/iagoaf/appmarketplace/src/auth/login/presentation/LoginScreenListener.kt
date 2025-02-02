package com.iagoaf.appmarketplace.src.auth.login.presentation

sealed class LoginScreenListener {
    object Idle : LoginScreenListener()
    data class Error(val errorMessage: String) : LoginScreenListener()
    data class Success(val data: String) : LoginScreenListener()
}