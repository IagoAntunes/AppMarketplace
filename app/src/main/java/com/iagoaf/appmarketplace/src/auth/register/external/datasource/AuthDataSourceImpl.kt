package com.iagoaf.appmarketplace.src.auth.register.external.datasource

import com.iagoaf.appmarketplace.core.result.Result
import com.iagoaf.appmarketplace.src.auth.register.infra.datasource.IAuthDataSource
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.exceptions.RestException
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class AuthDataSourceImpl(
    val supabase: SupabaseClient
) : IAuthDataSource {
    override suspend fun register(email: String, password: String): Result<Unit, Error> {
        try {
            supabase.auth.signUpWith(Email) {
                this.email = email
                this.password = password
                data = buildJsonObject {
                    put("email", email)
                }
            }
            return Result.Success(
                data = Unit
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
            val response = supabase.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
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
}