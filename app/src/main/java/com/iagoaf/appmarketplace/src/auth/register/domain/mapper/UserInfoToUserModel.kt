package com.iagoaf.appmarketplace.src.auth.register.domain.mapper

import com.iagoaf.appmarketplace.src.auth.register.domain.models.UserModel
import io.github.jan.supabase.auth.user.UserInfo

fun UserInfo.toUserModel(): UserModel {
    return UserModel(
        name = "",
        phone = this.phone ?: "",
        mail = this.email ?: "",
        password = ""
    )
}