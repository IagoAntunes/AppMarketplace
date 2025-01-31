package com.iagoaf.appmarketplace.src.auth.register.infra.repository

import com.iagoaf.appmarketplace.core.result.Result
import com.iagoaf.appmarketplace.src.auth.register.domain.repository.IAuthRepository
import com.iagoaf.appmarketplace.src.auth.register.infra.datasource.IAuthDataSource
import io.github.jan.supabase.auth.user.UserInfo

class AuthRepositoryImpl(
    val datasource: IAuthDataSource
) : IAuthRepository {
    override suspend fun register(email: String, password: String): Result<Unit, Error> {
        return datasource.register(email, password)
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit, Error> {
        return datasource.login(email, password)
    }
}