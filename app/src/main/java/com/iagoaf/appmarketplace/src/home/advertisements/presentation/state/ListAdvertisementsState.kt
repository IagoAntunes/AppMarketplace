package com.iagoaf.appmarketplace.src.home.advertisements.presentation.state

import com.iagoaf.appmarketplace.src.home.advertisements.domain.models.AdvertisementModel

sealed class ListAdvertisementsState{
    object Idle : ListAdvertisementsState()
    object Loading : ListAdvertisementsState()
    data class Success(val advertisements: List<AdvertisementModel>) : ListAdvertisementsState()
    data class Error(val message: String) : ListAdvertisementsState()
}