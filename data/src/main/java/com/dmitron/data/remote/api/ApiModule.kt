package com.dmitron.data.remote.api

import com.dmitron.data.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideRetrofit() }
    single { provideWeatherApiService(get()) }
}

fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService =
    retrofit.create(WeatherApiService::class.java)

fun provideRetrofit(): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()