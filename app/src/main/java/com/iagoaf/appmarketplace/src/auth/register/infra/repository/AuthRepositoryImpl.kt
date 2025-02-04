package com.iagoaf.appmarketplace.src.auth.register.infra.repository

import android.util.Log
import com.iagoaf.appmarketplace.core.result.Result
import com.iagoaf.appmarketplace.services.sharedPreferences.SharedPreferencesKeys
import com.iagoaf.appmarketplace.services.sharedPreferences.domain.ISharedPreferences
import com.iagoaf.appmarketplace.src.auth.register.domain.mapper.toUserEntity
import com.iagoaf.appmarketplace.src.auth.register.domain.mapper.toUserModel
import com.iagoaf.appmarketplace.src.auth.register.domain.models.UserModel
import com.iagoaf.appmarketplace.src.auth.register.domain.repository.IAuthRepository
import com.iagoaf.appmarketplace.src.auth.register.infra.datasource.IAuthDataSource

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

    override suspend fun logout(): Result<Unit, Error> {
        return datasource.logout()
    }

    override suspend fun currentUser(): Result<UserModel, Error> {
        val result = datasource.currentAuthUser()
        if (result is Result.Success) {
            sharedPreferences.saveData(SharedPreferencesKeys.USER_ID.nameKey, result.data.id)
            val resultGetUserById = datasource.getUserById(result.data.id)
            val userModel = result.data.toUserModel()
            if (resultGetUserById is Result.Success) {
                userModel.name = resultGetUserById.data.name
                userModel.phone = resultGetUserById.data.phone
            }
            return Result.Success(userModel)
        } else {
            return Result.Error((result as Result.Error).error)
        }
    }
}