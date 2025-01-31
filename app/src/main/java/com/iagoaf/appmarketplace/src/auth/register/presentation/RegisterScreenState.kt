package com.iagoaf.appmarketplace.src.auth.register.presentation

sealed class RegisterScreenState {
    object Idle : RegisterScreenState()
    object Loading : RegisterScreenState()
    object Success : RegisterScreenState()
    object Error : RegisterScreenState()
}