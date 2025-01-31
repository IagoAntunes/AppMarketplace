package com.iagoaf.appmarketplace.services.database

import org.koin.dsl.module

val databaseModule = module {
    single {
        AppSupabase.instance
    }
}