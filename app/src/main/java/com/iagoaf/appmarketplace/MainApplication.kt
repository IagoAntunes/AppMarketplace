package com.iagoaf.appmarketplace

import android.app.Application
import com.iagoaf.appmarketplace.src.splashScreen.presentation.module.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppMarketplaceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppMarketplaceApplication)
            androidLogger()

            modules(
                splashModule,
            )
        }
    }
}