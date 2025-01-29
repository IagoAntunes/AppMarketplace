package com.iagoaf.appmarketplace.src.splashScreen.presentation.action

sealed class SplashActions {
    object NavigateToLogin : SplashActions()
    object NavigateToHome : SplashActions()
}