package com.dmitron.domain

import com.dmitron.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {
    factory { GetCityWeatherByIdUseCase(get()) }
    factory { SearchCitiesUseCase(get()) }
    factory { SaveCityIdsUseCase(get()) }
    factory { GetCityIdsUseCase(get()) }
    factory { SearchPagedCitiesUseCase(get()) }
}