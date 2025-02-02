package com.iagoaf.appmarketplace.src.auth.register.domain.repository

import com.iagoaf.appmarketplace.core.result.Result
import com.iagoaf.appmarketplace.src.auth.register.domain.models.UserModel
import io.github.jan.supabase.auth.user.UserInfo

interface IAuthRepository {
    suspend fun register(user: UserModel): Result<Unit, Error>
    suspend fun login(email: String, password: String): Result<Unit, Error>
    suspend fun currentUser(): Result<UserInfo, Error>
}