package com.iagoaf.appmarketplace.src.auth.register

sealed class RegisterActions {
    data class Register(
        val name: String,
        val phone: String,
        val mail: String,
        val password: String
    ) : RegisterActions()

    object GoBackToLogin : RegisterActions()
    object ResetListener : RegisterActions()
}