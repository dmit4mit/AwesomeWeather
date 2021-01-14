package com.dmitron.data

import com.dmitron.data.local.CityWeatherLocalDataSource
import com.dmitron.data.local.CityWeatherRoomDataSource
import com.dmitron.data.remote.CityWeatherRemoteDataSource
import com.dmitron.data.remote.CityWeatherApiDataSource
import com.dmitron.domain.repository.CityWeatherRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single { CityWeatherRoomDataSource(get()) } bind CityWeatherLocalDataSource::class
    single { CityWeatherApiDataSource(get(), get()) } bind CityWeatherRemoteDataSource::class
    single { CityWeatherRepositoryImpl(get(), get()) } bind CityWeatherRepository::class
}