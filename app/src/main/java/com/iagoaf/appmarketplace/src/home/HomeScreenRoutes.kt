package com.iagoaf.appmarketplace.src.home

import androidx.annotation.DrawableRes
import com.iagoaf.appmarketplace.R

sealed class HomeScreenRoutes(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int
) {
    object Home :
        HomeScreenRoutes(route = "PRODUTOS", label = "PRODUTOS", icon = R.drawable.ic_store)

    object Profile :
        HomeScreenRoutes(route = "PERFIL", label = "PERFIL", icon = R.drawable.ic_user)
}