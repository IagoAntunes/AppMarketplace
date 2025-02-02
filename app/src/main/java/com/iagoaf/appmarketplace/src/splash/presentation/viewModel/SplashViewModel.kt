package com.iagoaf.appmarketplace.src.splash.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.iagoaf.appmarketplace.core.result.onError
import com.iagoaf.appmarketplace.core.result.onSuccess
import com.iagoaf.appmarketplace.core.routes.AppMarketplaceRoutes
import com.iagoaf.appmarketplace.src.auth.register.domain.repository.IAuthRepository
import com.iagoaf.appmarketplace.src.splash.presentation.action.SplashActions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    val authRepository: IAuthRepository
) : ViewModel() {

    lateinit var navController: NavController

    fun onAction(action: SplashActions) {
        when (action) {
            is SplashActions.NavigateToLogin -> {
                actionNavigateToLogin()
            }

            SplashActions.NavigateToHome -> {
                //
            }

            SplashActions.VerifyIsLogged -> {
                verifyIsLogged()
            }
        }
    }

    private fun actionNavigateToLogin() {
        navController.navigate(AppMarketplaceRoutes.LoginScreenRoute.name)
    }

    private fun actionNavigateToHome() {
        navController.navigate(AppMarketplaceRoutes.HomeScreenRoute.name)
    }

    private fun verifyIsLogged() {
        viewModelScope.launch {
            delay(2000)
            authRepository.currentUser()
                .onSuccess {
                    actionNavigateToHome()
                }
                .onError {
                    actionNavigateToLogin()
                }
        }
    }
}
