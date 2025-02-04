package com.iagoaf.appmarketplace.src.home.advertisements.external.datasource

import android.util.Log
import com.iagoaf.appmarketplace.core.result.Result
import com.iagoaf.appmarketplace.services.server.DatabaseTables
import com.iagoaf.appmarketplace.src.home.advertisements.domain.entities.AdvertisementEntity
import com.iagoaf.appmarketplace.src.home.advertisements.infra.datasource.IAdvertisementDataSource
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.Json

@Suppress("JSON_FORMAT_REDUNDANT_DEFAULT")
class AdvertisementDataSourceImpl(
    val server: SupabaseClient
) : IAdvertisementDataSource {
    override suspend fun getAnnouncements(): Result<List<AdvertisementEntity>, Error> {
        try {
            val response = server.postgrest.from(DatabaseTables.ADVERTISEMENTS.nameTable).select()
            Log.w("RESPONSE", "response -> ${response.data}")
            val json = Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
            val listAdvertisements: List<AdvertisementEntity> = json.decodeFromString(response.data)

            Log.w("RESPONSE", "getAnnouncements -> $listAdvertisements")

            return Result.Success(listAdvertisements)

        } catch (e: RestException) {
            Log.w("RESPONSE", "getAnnouncements error -> ${e.message}")
            return Result.Error(Error(e.message ?: "Unknown error"))
        } catch (e: Exception) {
            Log.w("RESPONSE", "getAnnouncements error -> ${e.message}")
            return Result.Error(Error(e.message ?: "Unknown error"))
        }
    }
}