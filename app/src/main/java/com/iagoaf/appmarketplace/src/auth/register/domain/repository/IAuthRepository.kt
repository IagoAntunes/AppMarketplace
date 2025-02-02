package com.iagoaf.appmarketplace.src.auth.register.domain.repository

import com.iagoaf.appmarketplace.core.result.Result
import com.iagoaf.appmarketplace.src.auth.register.domain.models.UserModel

interface IAuthRepository {
    suspend fun register(user: UserModel): Result<Unit, Error>
    suspend fun login(email: String, password: String): Result<Unit, Error>
}