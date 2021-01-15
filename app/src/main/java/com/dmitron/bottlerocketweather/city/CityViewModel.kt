package com.dmitron.bottlerocketweather.city

import androidx.lifecycle.*
import com.dmitron.bottlerocketweather.base.BaseViewModel
import com.dmitron.bottlerocketweather.utils.DateTimeUtils
import com.dmitron.common.ErrorType
import com.dmitron.common.ResultWrapper
import com.dmitron.domain.usecases.GetCityWeatherByIdUseCase
import kotlinx.coroutines.flow.collect

class CityViewModel(
    private val getCityWeatherByIdUseCase: GetCityWeatherByIdUseCase
) : BaseViewModel<CityViewModel.CityScreenEvent>() {

    private val cityId = MutableLiveData<Long>()

    private val _isLoading = MutableLiveData<Boolean>()
    private val isLoading: LiveData<Boolean> = _isLoading

    val cityWeather = cityId.switchMap { id ->
        liveData {
            getCityWeatherByIdUseCase(GetCityWeatherByIdUseCase.Params(id)).collect {
                when(it) {
                    is ResultWrapper.Success -> emit(it.data).also { setLoading(false) }
                    is ResultWrapper.Failure -> handleFailure(it.errorType)
                    ResultWrapper.Loading -> setLoading(true)
                }
            }
        }
    }

    val city = cityWeather.map { it.city }
    val weather = cityWeather.map { it.weather }

    val name = city.map {"${it.name}, ${it.countryCode}" }

    val date = city.map { DateTimeUtils.formatCurrentDate(it.timezone) }
    val time = city.map { DateTimeUtils.formatCurrentTime(it.timezone) }
    val currentWeather = weather.map {  }

    init {
        cityId.value = 4047914
    }

    private fun handleFailure(failure: ErrorType) {
        showSnackbar(failure.getMessage())
        setLoading(false)
    }

    private fun setLoading(value: Boolean) {
        _isLoading.value = value
    }

    sealed class CityScreenEvent {

    }
}