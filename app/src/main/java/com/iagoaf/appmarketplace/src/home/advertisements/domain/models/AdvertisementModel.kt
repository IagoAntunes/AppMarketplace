package com.iagoaf.appmarketplace.src.home.advertisements.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class AdvertisementModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val category: String,
    val countViewedProduct: Int,
    val price: String,
    val userAnnouncerId: String
)
