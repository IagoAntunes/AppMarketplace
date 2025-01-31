package com.iagoaf.appmarketplace.src.auth.register

sealed class RegisterActions {
    data class Register(
        val email: String,
        val password: String,
    ) : RegisterActions()

    object GoBackToLogin : RegisterActions()
    object ResetListener : RegisterActions()
}