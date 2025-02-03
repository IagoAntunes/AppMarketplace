package com.iagoaf.appmarketplace.src.auth.register.domain.mapper

import com.iagoaf.appmarketplace.services.server.domain.entities.UserEntity
import com.iagoaf.appmarketplace.src.auth.register.domain.models.UserModel

fun UserModel.toUserEntity(userId: String): UserEntity {
    return UserEntity(
        userId = userId,
        name = this.name,
        phone = this.phone,
        mail = this.mail,
    )
}