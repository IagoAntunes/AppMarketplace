package com.iagoaf.appmarketplace.src.auth.register.external.datasource

import android.util.Log
import com.iagoaf.appmarketplace.core.result.Result
import com.iagoaf.appmarketplace.services.server.DatabaseTables
import com.iagoaf.appmarketplace.services.server.domain.entities.UserEntity
import com.iagoaf.appmarketplace.src.auth.register.infra.datasource.IAuthDataSource
import com.iagoaf.appmarketplace.src.home.profile.domain.models.ProfileModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.user.UserInfo
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.Json

class AuthDataSourceImpl(
    val server: SupabaseClient
) : IAuthDataSource {
    override suspend fun register(email: String, password: String): Result<UserInfo, Error> {
        try {
            val response = server.auth.signUpWith(Email) {
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
            server.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }

            val currentUser = server.auth.currentUserOrNull()
            val uid = currentUser!!.id

            Log.w("RESPONSE UID", uid)
            server.auth.currentAccessTokenOrNull()

            return Result.Success(
                data = Unit
            )
        } catch (e: RestException) {
            return Result.Error(Error(e.message ?: "Unknown error"))
        } catch (e: Exception) {
            return Result.Error(Error(e.message ?: "Unknown error"))
        }
    }

    override suspend fun logout(): Result<Unit, Error> {
        try {
            server.auth.signOut()
            return Result.Success(Unit)
        } catch (e: RestException) {
            return Result.Error(Error(e.message ?: "Unknown error"))
        } catch (e: Exception) {
            return Result.Error(Error(e.message ?: "Unknown error"))
        }
    }

    override suspend fun currentAuthUser(): Result<UserInfo, Error> {
        try {
            val user = server.auth.currentUserOrNull()
            Log.w("CURRENTUSER", user.toString())
            if (user == null) {
                throw Exception("User not found")
            }

            return Result.Success(
                data = user
            )
        } catch (e: RestException) {
            Log.w("RESPONSE", "CURRENT USER error -> ${e.message}")
            return Result.Error(Error(e.message ?: "Unknown error"))
        } catch (e: Exception) {
            Log.w("RESPONSE", "CURRENT USER error -> ${e.message}")
            return Result.Error(Error(e.message ?: "Unknown error"))
        }
    }

    override suspend fun getUserById(id: String): Result<ProfileModel, Error> {
        try {
            val response = server.postgrest.from(DatabaseTables.USERS.nameTable)
                .select {
                    filter {
                        eq("userId", id)
                    }
                }

            val json = Json { ignoreUnknownKeys = true }
            val listUsers: List<ProfileModel> = json.decodeFromString(response.data)
            if (listUsers.isEmpty()) {
                throw Exception("User not found")
            }
            return Result.Success(
                data = listUsers.first()
            )
        } catch (e: RestException) {
            Log.w("RESPONSE", "CURRENT USER error -> ${e.message}")
            return Result.Error(Error(e.message ?: "Unknown error"))
        } catch (e: Exception) {
            Log.w("RESPONSE", "CURRENT USER error -> ${e.message}")
            return Result.Error(Error(e.message ?: "Unknown error"))
        }
    }

    override suspend fun insertUserInfo(user: UserEntity): Result<Unit, Error> {
        try {
            Log.w("INSERTUSERTINFO", "ENTER INSERT")
            server.from(DatabaseTables.USERS.nameTable)
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