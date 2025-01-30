package com.iagoaf.appmarketplace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iagoaf.appmarketplace.core.routes.AppMarketplaceRoutes
import com.iagoaf.appmarketplace.core.ui.theme.AppMarketplaceTheme
import com.iagoaf.appmarketplace.src.auth.login.presentation.screen.LoginScreen
import com.iagoaf.appmarketplace.src.auth.login.presentation.viewModel.LoginViewModel
import com.iagoaf.appmarketplace.src.auth.register.presentation.RegisterScreen
import com.iagoaf.appmarketplace.src.auth.register.presentation.RegisterViewModel
import com.iagoaf.appmarketplace.src.splash.presentation.screen.SplashScreen
import com.iagoaf.appmarketplace.src.splash.presentation.viewModel.SplashViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppMarketplaceTheme {
                NavigationStack()
            }
        }
    }
}

@Composable
fun NavigationStack() {
    val navController = rememberNavController()
    NavHost(
        startDestination = AppMarketplaceRoutes.SplashScreenRoute.name,
        navController = navController,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        composable(route = AppMarketplaceRoutes.SplashScreenRoute.name) {
            val viewModel = koinViewModel<SplashViewModel>()
            viewModel.navController = navController
            SplashScreen(onAction = viewModel::onAction)
        }
        composable(route = AppMarketplaceRoutes.LoginScreenRoute.name) {
            val viewModel = koinViewModel<LoginViewModel>()
            viewModel.navController = navController
            LoginScreen(
                onAction = viewModel::onAction
            )
        }
        composable(route = AppMarketplaceRoutes.RegisterScreenRoute.name) {
            val viewModel = koinViewModel<RegisterViewModel>()
            viewModel.navController = navController
            RegisterScreen(
                onAction = viewModel::onAction
            )
        }
    }
}