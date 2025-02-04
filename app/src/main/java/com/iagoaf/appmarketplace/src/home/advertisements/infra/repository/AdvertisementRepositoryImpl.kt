package com.iagoaf.appmarketplace.src.home.advertisements.infra.repository

import com.iagoaf.appmarketplace.core.result.Result
import com.iagoaf.appmarketplace.src.home.advertisements.domain.mappers.toAdvertisementModel
import com.iagoaf.appmarketplace.src.home.advertisements.domain.models.AdvertisementModel
import com.iagoaf.appmarketplace.src.home.advertisements.domain.repository.IAdvertisementRepository
import com.iagoaf.appmarketplace.src.home.advertisements.infra.datasource.IAdvertisementDataSource

class AdvertisementRepositoryImpl(
    private val advertisementDataSource: IAdvertisementDataSource
) : IAdvertisementRepository {
    override suspend fun getAnnouncements(): Result<List<AdvertisementModel>, Error> {
        val result = advertisementDataSource.getAnnouncements()

        if (result is Result.Success) {
            val advertisements = result.data.map { advertisementEntity ->
                advertisementEntity.toAdvertisementModel()
            }

            return Result.Success(advertisements)
        } else {
            val error = (result as Result.Error).error
            return Result.Error(error)
        }
    }
}