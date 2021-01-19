package com.dmitron.data.local.mappers

import com.dmitron.data.local.mappers.facade.DatabaseCityWeatherMapperFacade
import com.dmitron.data.local.model.*
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather
import com.dmitron.domain.models.Weather
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val DATABASE_CITY_WEATHER_LIST_MAPPER = "DATABASE_CITY_LIST_MAPPER"
private const val WEATHER_TO_DATABASE_MAPPER = "WEATHER_TO_DATABASE_MAPPER"
private const val CITY_TO_DATABASE_MAPPER = "CITY_TO_DATABASE_MAPPER"
private const val DAY_TO_DATABASE_MAPPER = "DAY_TO_DATABASE_MAPPER"
private const val HOUR_TO_DATABASE_MAPPER = "HOUR_TO_DATABASE_MAPPER"

val localMappersModule = module {
    factory {
        DatabaseCityWeatherMapperFacade(
            get(named(DATABASE_CITY_WEATHER_LIST_MAPPER)),
            get(named(CITY_TO_DATABASE_MAPPER)),
            get(named(WEATHER_TO_DATABASE_MAPPER)),
            get(named(DAY_TO_DATABASE_MAPPER)),
            get(named(HOUR_TO_DATABASE_MAPPER))
        )
    }

    factory(named(DATABASE_CITY_WEATHER_LIST_MAPPER)) { makeDatabaseCityWeatherMapper() }
    factory(named(CITY_TO_DATABASE_MAPPER)) { makeCityToDatabaseMapper() }
    factory(named(WEATHER_TO_DATABASE_MAPPER)) { makeWeatherToDatabaseMapper() }
    factory(named(DAY_TO_DATABASE_MAPPER)) { makeDayToDatabaseMapper() }
    factory(named(HOUR_TO_DATABASE_MAPPER)) { makeHourToDatabaseMapper() }
}

fun makeHourToDatabaseMapper(): (
    Weather.Day.HourlyWeather, dayId: Long, cityId: Long,
    parentDayOfWeek: Int,
    parentWeatherId: Long
) -> DatabaseHourlyWeather = ::mapHourToDatabase

fun makeDayToDatabaseMapper(): (Weather.Day, weatherId: Long, cityId: Long) -> DatabaseDay =
    ::mapDayToDatabase


fun makeWeatherToDatabaseMapper(): (Weather, cityId: Long) -> DatabaseWeather {
    return ::mapWeatherToDatabase
}

fun makeCityToDatabaseMapper(): (City) -> DatabaseCity {
    return ::mapCityToDatabase
}

internal fun makeDatabaseCityWeatherMapper(): (DatabaseCityWeather) -> CityWeather = {
    mapDatabaseCityWeather(it)
}