package com.dmitron.domain.usecases

import com.dmitron.common.ResultWrapper
import com.dmitron.domain.models.CityWeather
import com.dmitron.domain.repository.CityWeatherRepository
import kotlinx.coroutines.flow.Flow

class GetCityWeatherByIdUseCase(private val repository: CityWeatherRepository) :
    ParamsUseCase<CityWeather, GetCityWeatherByIdUseCase.Params> {

    override suspend fun invoke(params: Params): Flow<ResultWrapper<CityWeather>> {
        return repository.getCityWeatherById(params.cityId)
    }

    data class Params(val cityId: Long)
}