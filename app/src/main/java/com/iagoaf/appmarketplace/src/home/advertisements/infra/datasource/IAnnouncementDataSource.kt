package com.iagoaf.appmarketplace.src.home.advertisements.infra.datasource

import com.iagoaf.appmarketplace.core.result.Result
import com.iagoaf.appmarketplace.src.home.advertisements.domain.entities.AdvertisementEntity

interface IAdvertisementDataSource {
    suspend fun getAnnouncements(): Result<List<AdvertisementEntity>, Error>
}