package com.iagoaf.appmarketplace.src.home.profile

import com.iagoaf.appmarketplace.src.home.profile.presentation.viewModel.ProfileViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val profileModule = module {
    singleOf(::ProfileViewModel)
}