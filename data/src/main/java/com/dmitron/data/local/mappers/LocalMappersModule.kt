//package com.dmitron.data.local.mappers
//
//import com.dmitron.data.local.mappers.facade.DatabaseCityWeatherMapperFacade
//import com.dmitron.data.remote.mappers.makeNetworkCityWeatherMapper
//import org.koin.core.qualifier.named
//import org.koin.dsl.module
//
//private const val CITY_WEATHER_TO_DATABASE_MAPPER = "CITY_WEATHER_TO_DATABASE_MAPPER"
//private const val DATABASE_CITY_WEATHER_LIST_MAPPER = "DATABASE_CITY_LIST_MAPPER"
//
//val localMappersModule = module {
//    factory {
//        DatabaseCityWeatherMapperFacade(
//            get(named(CITY_WEATHER_TO_DATABASE_MAPPER)),
//            get(named(DATABASE_CITY_WEATHER_LIST_MAPPER))
//        )
//    }
//
//    factory(named(CITY_WEATHER_TO_DATABASE_MAPPER)) { makeCityWeatherToDatabaseMapper() }
//    factory(named(DATABASE_CITY_WEATHER_LIST_MAPPER)) { makeDatabaseCityWeatherMapper() }
//}