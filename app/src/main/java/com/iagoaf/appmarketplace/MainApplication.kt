package com.iagoaf.appmarketplace

import android.app.Application
import com.iagoaf.appmarketplace.services.server.serverModule
import com.iagoaf.appmarketplace.services.sharedPreferences.sharedPrefsModule
import com.iagoaf.appmarketplace.src.auth.authModule
import com.iagoaf.appmarketplace.src.home.profile.profileModule
import com.iagoaf.appmarketplace.src.splash.presentation.module.splashModule
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
                serverModule,
                sharedPrefsModule,
                splashModule,
                authModule,
                profileModule
            )
        }
    }
}