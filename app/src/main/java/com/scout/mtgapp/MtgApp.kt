package com.scout.mtgapp

import android.app.Application
import com.scout.mtgapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MtgApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MtgApp)
            modules(appModule)
        }
    }
}