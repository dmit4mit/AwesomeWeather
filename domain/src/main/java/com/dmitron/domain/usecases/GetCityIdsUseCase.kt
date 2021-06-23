package com.dmitron.domain.usecases

import com.dmitron.domain.repository.CityWeatherRepository

class GetCityIdsUseCase(private val repository: CityWeatherRepository) {
    operator fun invoke(): List<Long> {
        return repository.getSavedCitiesIds()
    }
}