package com.iagoaf.appmarketplace.services.sharedPreferences

import com.iagoaf.appmarketplace.services.sharedPreferences.domain.ISharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPrefsModule = module {
    single<ISharedPreferences> { PreferencesManager(androidContext()) }
}
