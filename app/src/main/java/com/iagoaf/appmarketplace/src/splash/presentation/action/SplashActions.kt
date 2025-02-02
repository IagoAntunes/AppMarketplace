package com.iagoaf.appmarketplace.src.splash.presentation.action

sealed class SplashActions {
    object NavigateToLogin : SplashActions()
    object NavigateToHome : SplashActions()
    object VerifyIsLogged : SplashActions()
}