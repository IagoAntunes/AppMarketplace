package com.iagoaf.appmarketplace.src.auth.register.presentation

sealed class RegisterScreenListener {
    object Idle : RegisterScreenListener()
    object SuccessRegister : RegisterScreenListener()
    data class ErrorRegister(val errorMessage: String) : RegisterScreenListener()
}