package com.iagoaf.appmarketplace.src.auth.login.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.iagoaf.appmarketplace.core.routes.AppMarketplaceRoutes
import com.iagoaf.appmarketplace.src.auth.login.presentation.LoginActions

class LoginViewModel : ViewModel() {

    lateinit var navController: NavController

    fun onAction(action: LoginActions) {
        when (action) {
            is LoginActions.NavigateToRegister -> {
                navigateToRegister()
            }
        }
    }

    private fun navigateToRegister() {
        navController.navigate(AppMarketplaceRoutes.RegisterScreenRoute.name)
    }

}