package com.iagoaf.appmarketplace.src.auth

import com.iagoaf.appmarketplace.src.auth.login.presentation.viewModel.LoginViewModel
import com.iagoaf.appmarketplace.src.auth.register.presentation.RegisterViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    singleOf(::LoginViewModel)
    singleOf(::RegisterViewModel)
}