package com.dmitron.data

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.dmitron.data.local.AppDatabase
import com.dmitron.data.local.CitySharedPrefDataSource
import com.dmitron.data.local.CityWeatherLocalDataSource
import com.dmitron.data.local.CityWeatherRoomDataSource
import com.dmitron.data.remote.CityWeatherRemoteDataSource
import com.dmitron.data.remote.CityWeatherApiDataSource
import com.dmitron.domain.repository.CityWeatherRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single { CityWeatherRoomDataSource(get(), get()) } bind CityWeatherLocalDataSource::class
    single { CityWeatherApiDataSource(get(), get(), get()) } bind CityWeatherRemoteDataSource::class
    single { CityWeatherRepositoryImpl(get(), get(), get()) } bind CityWeatherRepository::class
    single { CitySharedPrefDataSource() }
    factory { (prefName: String) -> provideSharedPrefs(androidContext(), prefName) }
    single { provideAppDatabase(androidContext()) }
    single { provideCityWeatherDao(get()) }
}

fun provideCityWeatherDao(appDatabase: AppDatabase) = appDatabase.cityWeatherDao()

fun provideAppDatabase(context: Context) = Room.databaseBuilder(
    context,
    AppDatabase::class.java, "weather-database"
).build()

fun provideSharedPrefs(context: Context, prefName: String): SharedPreferences {
    return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
}
