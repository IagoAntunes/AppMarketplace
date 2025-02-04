package com.iagoaf.appmarketplace.src.auth.register.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    var name: String,
    var phone: String,
    val mail: String,
    val password: String = ""
)