package com.dmitron.domain

import com.dmitron.domain.usecases.GetCityWeatherByIdUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCityWeatherByIdUseCase(get()) }
}