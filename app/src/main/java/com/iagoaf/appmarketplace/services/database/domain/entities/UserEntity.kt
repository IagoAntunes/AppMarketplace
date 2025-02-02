package com.iagoaf.appmarketplace.services.database.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val userId: String,
    val name: String,
    val phone: String,
    val mail: String,
)