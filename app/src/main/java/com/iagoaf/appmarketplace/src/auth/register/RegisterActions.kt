package com.iagoaf.appmarketplace.src.auth.register

sealed class RegisterActions {
    object Register : RegisterActions()
    object GoBackToLogin : RegisterActions()
}