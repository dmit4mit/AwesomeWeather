package com.dmitron.bottlerocketweather

import android.app.Application
import com.dmitron.bottlerocketweather.di.viewModelModule
import com.dmitron.data.local.mappers.facade.localMappersModule
import com.dmitron.data.remote.api.networkModule
import com.dmitron.data.remote.mappers.networkMappersModule
import com.dmitron.data.repositoryModule
import com.dmitron.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApplication)
            modules(
                viewModelModule
            )
        }
    }
}