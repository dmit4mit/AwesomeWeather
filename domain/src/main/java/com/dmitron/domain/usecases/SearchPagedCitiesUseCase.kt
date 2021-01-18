package com.dmitron.domain.usecases

import com.dmitron.domain.repository.CityWeatherRepository
import kotlinx.coroutines.CoroutineScope

class SearchPagedCitiesUseCase(private val repository: CityWeatherRepository) {

    operator fun invoke(params: Params) =
        repository.searchPagedCities(params.query, params.scope)

    data class Params(val scope: CoroutineScope, val query: String)
}