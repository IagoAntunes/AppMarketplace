package com.iagoaf.appmarketplace.src.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iagoaf.appmarketplace.core.ui.theme.AppMarketplaceTheme
import com.iagoaf.appmarketplace.core.ui.theme.gray100
import com.iagoaf.appmarketplace.core.ui.theme.orangeBase
import com.iagoaf.appmarketplace.core.ui.theme.typography
import com.iagoaf.appmarketplace.core.ui.theme.white
import com.iagoaf.appmarketplace.src.home.advertisements.presentation.viewModel.AdvertisementsViewModel
import com.iagoaf.appmarketplace.src.home.advertisements.presentation.screens.HomeNavItem
import com.iagoaf.appmarketplace.src.home.profile.presentation.screens.ProfileNavItem
import com.iagoaf.appmarketplace.src.home.profile.presentation.viewModel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeNavManager(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val homeManagerNavController = rememberNavController()
    val navBackStackEntry by homeManagerNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val routes = listOf(
        HomeScreenRoutes.Home,
        HomeScreenRoutes.Profile
    )

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(
                contentColor = white,
                containerColor = white,
            ) {
                routes.forEach { route ->
                    NavigationBarItem(
                        selected = currentRoute == route.route,
                        onClick = {
                            if (currentRoute != route.route) {
                                homeManagerNavController.navigate(route.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(homeManagerNavController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                }
                            }
                        },
                        icon = {
                            Image(
                                painter = painterResource(route.icon),
                                contentDescription = route.label,
                                colorFilter = if (currentRoute == route.route) ColorFilter.tint(
                                    orangeBase
                                ) else ColorFilter.tint(
                                    gray100
                                )
                            )
                        },
                        label = {
                            Text(
                                text = route.label,
                                style = typography.labelMedium,
                                color = if (currentRoute == route.route) orangeBase else gray100,
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            unselectedIconColor = gray100,
                            selectedIconColor = orangeBase,
                            unselectedTextColor = gray100,
                            selectedTextColor = orangeBase,
                            indicatorColor = Color.Transparent
                        ),
                    )
                }

            }
        }
    ) { innerPadding ->
        NavHost(
            startDestination = HomeScreenRoutes.Home.route,
            navController = homeManagerNavController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = HomeScreenRoutes.Home.route) {
                val advertisementsViewModel = koinViewModel<AdvertisementsViewModel>()
                advertisementsViewModel.navController = navController
                HomeNavItem(
                    advertisementsViewModel = advertisementsViewModel,
                )
            }
            composable(route = HomeScreenRoutes.Profile.route) {
                val viewModel = koinViewModel<ProfileViewModel>()
                viewModel.appNavController = navController
                val state = viewModel.state.collectAsState()
                ProfileNavItem(
                    onAction = viewModel::onAction,
                    state = state.value,
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppMarketplaceTheme {
        HomeNavManager(
            navController = rememberNavController()
        )
    }
}