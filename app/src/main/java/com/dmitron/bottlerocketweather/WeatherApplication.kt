package com.dmitron.bottlerocketweather

import android.app.Application
import com.dmitron.bottlerocketweather.di.viewModelModule
import com.dmitron.data.local.mappers.localMappersModule
import com.dmitron.data.remote.api.networkModule
import com.dmitron.data.remote.mappers.networkMappersModule
import com.dmitron.data.repositoryModule
import com.dmitron.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@WeatherApplication)
            modules(
                viewModelModule,
                domainModule,
                networkModule,
                repositoryModule,
                networkMappersModule,
                localMappersModule
            )
        }
    }
}