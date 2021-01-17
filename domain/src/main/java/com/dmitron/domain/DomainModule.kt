package com.dmitron.domain

import com.dmitron.domain.usecases.GetCityWeatherByIdUseCase
import com.dmitron.domain.usecases.SearchCitiesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCityWeatherByIdUseCase(get()) }
    factory { SearchCitiesUseCase(get()) }
}