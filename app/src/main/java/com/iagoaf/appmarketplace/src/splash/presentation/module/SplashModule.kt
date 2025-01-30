package com.iagoaf.appmarketplace.src.splash.presentation.module

import com.iagoaf.appmarketplace.src.splash.presentation.viewModel.SplashViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val splashModule = module {
    singleOf(::SplashViewModel)
}