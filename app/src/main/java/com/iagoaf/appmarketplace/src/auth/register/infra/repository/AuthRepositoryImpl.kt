package com.iagoaf.appmarketplace.src.auth.register.infra.repository

import android.util.Log
import com.iagoaf.appmarketplace.core.result.Result
import com.iagoaf.appmarketplace.services.sharedPreferences.SharedPreferencesKeys
import com.iagoaf.appmarketplace.services.sharedPreferences.domain.ISharedPreferences
import com.iagoaf.appmarketplace.src.auth.register.domain.mapper.toUserEntity
import com.iagoaf.appmarketplace.src.auth.register.domain.models.UserModel
import com.iagoaf.appmarketplace.src.auth.register.domain.repository.IAuthRepository
import com.iagoaf.appmarketplace.src.auth.register.infra.datasource.IAuthDataSource
import io.github.jan.supabase.auth.user.UserInfo

class AuthRepositoryImpl(
    val datasource: IAuthDataSource,
    val sharedPreferences: ISharedPreferences,
) : IAuthRepository {
    override suspend fun register(user: UserModel): Result<Unit, Error> {
        val result = datasource.register(user.mail, user.password)
        if (result is Result.Success) {
            Log.w("INSERT", "PRE INSERT")
            datasource.insertUserInfo(user.toUserEntity(result.data.id))
            return Result.Success(Unit)
        } else {

            return Result.Error((result as Result.Error).error)
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit, Error> {
        return datasource.login(email, password)
    }

    override suspend fun currentUser(): Result<UserInfo, Error> {
        val result = datasource.currentUser()
        if (result is Result.Success) {
            sharedPreferences.saveData(SharedPreferencesKeys.USER_ID.nameKey, result.data.id)
        }
        return result
    }
}