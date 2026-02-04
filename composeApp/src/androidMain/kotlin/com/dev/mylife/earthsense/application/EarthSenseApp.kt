package com.dev.mylife.earthsense.application

import android.app.Application
import com.dev.mylife.earthsense.di.initKoin
import org.koin.android.ext.koin.androidContext

class EarthSenseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@EarthSenseApp)
        }
    }
}