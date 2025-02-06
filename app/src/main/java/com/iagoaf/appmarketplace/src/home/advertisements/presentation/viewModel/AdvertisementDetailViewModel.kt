package com.iagoaf.appmarketplace.src.home.advertisements.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.iagoaf.appmarketplace.src.home.advertisements.presentation.actions.AdvertisementDetailActions

class AdvertisementDetailViewModel : ViewModel() {

    lateinit var navController: NavController

    fun onAction(action: AdvertisementDetailActions) {
        when (action) {
            AdvertisementDetailActions.GoBackToHome -> {
                navigateBackToHome()
            }
        }
    }

    private fun navigateBackToHome() {
        navController.popBackStack()
    }

}