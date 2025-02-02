package com.iagoaf.appmarketplace.src.auth.login.presentation

sealed class LoginScreenState {
    object Idle : LoginScreenState()
    object Loading : LoginScreenState()
}