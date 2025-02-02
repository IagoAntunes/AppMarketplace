package com.iagoaf.appmarketplace.src.auth.register.infra.datasource

import com.iagoaf.appmarketplace.core.result.Result
import com.iagoaf.appmarketplace.services.server.domain.entities.UserEntity
import io.github.jan.supabase.auth.user.UserInfo

interface IAuthDataSource {
    suspend fun register(email: String, password: String): Result<UserInfo, Error>
    suspend fun login(email: String, password: String): Result<Unit, Error>
    suspend fun currentUser(): Result<UserInfo, Error>
    suspend fun insertUserInfo(user: UserEntity): Result<Unit, Error>
}