package com.iagoaf.appmarketplace.src.auth.register.domain.repository

import com.iagoaf.appmarketplace.core.result.Result
import io.github.jan.supabase.auth.user.UserInfo

interface IAuthRepository {
    suspend fun register(email: String, password: String): Result<Unit, Error>
    suspend fun login(email: String, password: String): Result<Unit, Error>
}