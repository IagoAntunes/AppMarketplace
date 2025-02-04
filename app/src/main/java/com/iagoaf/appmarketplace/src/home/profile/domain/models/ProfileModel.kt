package com.iagoaf.appmarketplace.src.home.profile.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ProfileModel(
    val name: String,
    val phone: String,
    val mail: String,
)
