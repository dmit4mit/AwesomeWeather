package com.dmitron.domain.usecases

import com.dmitron.domain.repository.CityWeatherRepository

class SaveCityIdsUseCase(
    private val repository: CityWeatherRepository
) {
    operator fun invoke(params: Params) {
        repository.saveCitiesIds(params.cityIds.distinct())
    }

    data class Params(val cityIds: List<Long>)
}