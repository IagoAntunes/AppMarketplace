package com.iagoaf.appmarketplace.services.server

import org.koin.dsl.module

val serverModule = module {
    single {
        AppSupabase.instance
    }
}