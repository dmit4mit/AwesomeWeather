package com.dmitron.domain.usecases

import com.dmitron.common.ResultWrapper
import com.dmitron.domain.models.City
import com.dmitron.domain.repository.CityWeatherRepository
import com.dmitron.domain.usecases.interfaces.ParamsUseCase
import kotlinx.coroutines.flow.Flow

class SearchCitiesUseCase(private val repository: CityWeatherRepository) :
    ParamsUseCase<List<City>, SearchCitiesUseCase.Params> {

    override suspend fun invoke(params: Params): Flow<ResultWrapper<List<City>>> {
        return repository.searchCities(params.query)
    }

    data class Params(val query: String)
}