package com.iagoaf.appmarketplace.src.splash.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.iagoaf.appmarketplace.core.routes.AppMarketplaceRoutes
import com.iagoaf.appmarketplace.src.splash.presentation.action.SplashActions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    lateinit var navController: NavController

    fun onAction(action: SplashActions) {
        when (action) {
            is SplashActions.NavigateToLogin -> {
                actionNavigateToLogin()
            }

            SplashActions.NavigateToHome -> {
                //
            }
        }
    }

    private fun actionNavigateToLogin() {
        viewModelScope.launch {
            delay(2000)
            navController.navigate(AppMarketplaceRoutes.LoginScreenRoute.name)
        }
    }
}
