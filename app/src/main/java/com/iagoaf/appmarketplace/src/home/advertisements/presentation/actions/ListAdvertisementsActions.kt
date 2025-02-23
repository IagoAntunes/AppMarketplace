package com.iagoaf.appmarketplace.src.home.advertisements.presentation.actions

import com.iagoaf.appmarketplace.src.home.advertisements.domain.models.AdvertisementModel

sealed class ListAdvertisementsActions {
    object GetAll : ListAdvertisementsActions()
    data class FilterList(val filterValue: String) : ListAdvertisementsActions()
    data class NavigateToAdvertisementDetail(val advertisement: AdvertisementModel) :
        ListAdvertisementsActions()
}