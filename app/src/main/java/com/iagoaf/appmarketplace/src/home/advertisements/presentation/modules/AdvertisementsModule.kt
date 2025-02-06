package com.iagoaf.appmarketplace.src.home.advertisements.presentation.modules

import com.iagoaf.appmarketplace.src.home.advertisements.domain.repository.IAdvertisementRepository
import com.iagoaf.appmarketplace.src.home.advertisements.external.datasource.AdvertisementDataSourceImpl
import com.iagoaf.appmarketplace.src.home.advertisements.infra.datasource.IAdvertisementDataSource
import com.iagoaf.appmarketplace.src.home.advertisements.infra.repository.AdvertisementRepositoryImpl
import com.iagoaf.appmarketplace.src.home.advertisements.presentation.viewModel.AdvertisementDetailViewModel
import com.iagoaf.appmarketplace.src.home.advertisements.presentation.viewModel.AdvertisementsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val advertisementModule = module {
    singleOf(::AdvertisementRepositoryImpl).bind<IAdvertisementRepository>()
    singleOf(::AdvertisementDataSourceImpl).bind<IAdvertisementDataSource>()
    singleOf(::AdvertisementsViewModel)
    singleOf(::AdvertisementDetailViewModel)
}