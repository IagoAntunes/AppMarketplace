package com.iagoaf.appmarketplace.core.routes

sealed class AppMarketplaceRoutes(
    val name: String
) {
    object SplashScreenRoute : AppMarketplaceRoutes("splash_screen")
    object LoginScreenRoute : AppMarketplaceRoutes("login_screen")
    object RegisterScreenRoute : AppMarketplaceRoutes("register_screen")
}