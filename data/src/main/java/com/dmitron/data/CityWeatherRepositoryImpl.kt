package com.dmitron.data

import com.dmitron.common.ResultWrapper
import com.dmitron.data.local.CityWeatherLocalDataSource
import com.dmitron.data.remote.CityWeatherRemoteDataSource
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather
import com.dmitron.domain.repository.CityWeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CityWeatherRepositoryImpl(
    private val localSource: CityWeatherLocalDataSource,
    private val remoteSource: CityWeatherRemoteDataSource,
) : CityWeatherRepository {
    private val dispatcher = Dispatchers.IO

    override suspend fun getAllCities(): Flow<ResultWrapper<List<City>>> = flow {
        emit(ResultWrapper.Loading)
        emit(remoteSource.getAllCities())
    }.flowOn(dispatcher)

    override suspend fun getCityWeathersById(ids: List<String>): Flow<ResultWrapper<List<CityWeather>>> = flow {
        emit(ResultWrapper.Loading)
    }.flowOn(dispatcher)

    override suspend fun getCityWeatherById(cityId: String): Flow<ResultWrapper<CityWeather>> = flow {
        emit(ResultWrapper.Loading)
    }.flowOn(dispatcher)

    override suspend fun searchCities(query: String): Flow<ResultWrapper<List<City>>> = flow {
        emit(ResultWrapper.Loading)
        emit(remoteSource.searchCities(query))
    }.flowOn(dispatcher)
}