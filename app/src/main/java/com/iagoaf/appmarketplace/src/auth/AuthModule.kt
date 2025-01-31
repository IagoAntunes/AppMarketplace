package com.iagoaf.appmarketplace.src.auth

import com.iagoaf.appmarketplace.src.auth.login.presentation.viewModel.LoginViewModel
import com.iagoaf.appmarketplace.src.auth.register.domain.repository.IAuthRepository
import com.iagoaf.appmarketplace.src.auth.register.external.datasource.AuthDataSourceImpl
import com.iagoaf.appmarketplace.src.auth.register.infra.datasource.IAuthDataSource
import com.iagoaf.appmarketplace.src.auth.register.infra.repository.AuthRepositoryImpl
import com.iagoaf.appmarketplace.src.auth.register.presentation.RegisterViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authModule = module {
    singleOf(::AuthRepositoryImpl).bind<IAuthRepository>()
    singleOf(::AuthDataSourceImpl).bind<IAuthDataSource>()
    singleOf(::LoginViewModel)
    singleOf(::RegisterViewModel)

}