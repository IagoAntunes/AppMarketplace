package com.iagoaf.appmarketplace.src.home.advertisements.domain.mappers

import com.iagoaf.appmarketplace.src.home.advertisements.domain.entities.AdvertisementEntity
import com.iagoaf.appmarketplace.src.home.advertisements.domain.models.AdvertisementModel

fun AdvertisementEntity.toAdvertisementModel() = AdvertisementModel(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
    category = category,
    countViewedProduct = countViewedProduct,
    price = price,
    userAnnouncerId = userAnnouncerId
)