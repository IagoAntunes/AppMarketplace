package com.iagoaf.appmarketplace.src.auth.register.domain.models

import java.math.BigInteger

data class UserModel(
    val name: String,
    val phone: String,
    val mail: String,
    val password: String
)