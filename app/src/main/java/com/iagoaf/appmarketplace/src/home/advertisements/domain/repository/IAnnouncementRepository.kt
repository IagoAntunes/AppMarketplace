package com.iagoaf.appmarketplace.src.home.advertisements.domain.repository

import com.iagoaf.appmarketplace.core.result.Result
import com.iagoaf.appmarketplace.src.home.advertisements.domain.models.AdvertisementModel

interface IAdvertisementRepository {
    suspend fun getAnnouncements(): Result<List<AdvertisementModel>, Error>
}