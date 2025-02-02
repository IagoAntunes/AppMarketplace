package com.iagoaf.appmarketplace.src.auth.register.external.datasource

import android.util.Log
import com.iagoaf.appmarketplace.core.result.Result
import com.iagoaf.appmarketplace.services.database.DatabaseTables
import com.iagoaf.appmarketplace.services.database.domain.entities.UserEntity
import com.iagoaf.appmarketplace.src.auth.register.infra.datasource.IAuthDataSource
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.user.UserInfo
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.postgrest.from

class AuthDataSourceImpl(
    val supabase: SupabaseClient
) : IAuthDataSource {
    override suspend fun register(email: String, password: String): Result<UserInfo, Error> {
        try {
            val response = supabase.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            Log.w("RESPONSE", response.toString())
            return Result.Success(
                data = response!!
            )
        } catch (e: RestException) {
            return Result.Error(Error(e.message ?: "Unknown error"))
        } catch (e: Exception) {
            return Result.Error(Error(e.message ?: "Unknown error"))
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit, Error> {
        try {
            supabase.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }

            val currentUser = supabase.auth.currentUserOrNull()
            val uid = currentUser!!.id

            Log.w("RESPONSE UID", uid)
            supabase.auth.currentAccessTokenOrNull()

            return Result.Success(
                data = Unit
            )
        } catch (e: RestException) {
            return Result.Error(Error(e.message ?: "Unknown error"))
        } catch (e: Exception) {
            return Result.Error(Error(e.message ?: "Unknown error"))
        }
    }

    override suspend fun insertUserInfo(user: UserEntity): Result<Unit, Error> {
        try {
            Log.w("INSERTUSERTINFO", "ENTER INSERT")
            supabase.from(DatabaseTables.USERS.nameTable)
                .insert(user)

            Log.w("RESPONSE", "User inserted")
            return Result.Success(
                data = Unit
            )
        } catch (e: RestException) {
            Log.w("RESPONSE", "User INSERT error -> ${e.message}")
            return Result.Error(Error(e.message ?: "Unknown error"))
        } catch (e: Exception) {
            Log.w("RESPONSE", "User INSERT error -> ${e.message}")
            return Result.Error(Error(e.message ?: "Unknown error"))
        }
    }
}