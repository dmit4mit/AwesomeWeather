package com.dmitron.bottlerocketweather.city

import androidx.lifecycle.*
import com.dmitron.bottlerocketweather.base.BaseViewModel
import com.dmitron.bottlerocketweather.city.hourlyadapter.toViewData
import com.dmitron.bottlerocketweather.city.daysAdapter.toViewData
import com.dmitron.bottlerocketweather.utils.CombinedLiveData
import com.dmitron.bottlerocketweather.utils.DateTimeUtils
import com.dmitron.bottlerocketweather.utils.DisplayUtils
import com.dmitron.bottlerocketweather.utils.DisplayUtils.Density.*
import com.dmitron.common.ErrorType
import com.dmitron.common.ResultWrapper
import com.dmitron.domain.models.City
import com.dmitron.domain.models.Weather
import com.dmitron.domain.usecases.GetCityWeatherByIdUseCase
import kotlinx.coroutines.flow.collect

class CityViewModel(
    private val getCityWeatherByIdUseCase: GetCityWeatherByIdUseCase
) : BaseViewModel<CityViewModel.CityScreenEvent>() {
    private val cityId = MutableLiveData<Long>()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    val cityWeather = cityId.switchMap { id ->
        liveData {
            getCityWeatherByIdUseCase(GetCityWeatherByIdUseCase.Params(id)).collect {
                when (it) {
                    is ResultWrapper.Success -> emit(it.data).also { setLoading(false) }
                    is ResultWrapper.Failure -> handleFailure(it.errorType)
                    ResultWrapper.Loading -> setLoading(true)
                }
            }
        }
    }

    val city = cityWeather.map { it.city }
    val weather = cityWeather.map { it.weather }

    val timezone = city.map { it.timezone }
    val name = city.map { "${it.name}, ${it.countryCode}" }
    val date = city.map { DateTimeUtils.formatCurrentDate(it.timezone) }
    val time = city.map { DateTimeUtils.formatCurrentTime(it.timezone) }

    private val selectedDayOfWeek = MutableLiveData(DateTimeUtils.getTodayDayOfWeek())

    val weatherToday = weather.map { weather ->
        val day = weather.retrieveWeatherDay(DateTimeUtils.getTodayDayOfWeek())
        day ?: weather.days.first()
    }

    // todo: update every hour if needed
    val currentWeather = weatherToday.map { today ->
        val temp = today.hourlyWeather.find {
            it.hour == DateTimeUtils.getCurrentHourIn24Format(timezone.value.orEmpty())
        }?.temperature ?: 0
        "$temp°"
    }

    val cityImage = city.map { chooseImageDensity(it.imageURLs) }
    val hourlyWeather = CombinedLiveData(weather, selectedDayOfWeek) { weather, chosenDay ->
        weather
            ?.retrieveWeatherDay(chosenDay ?: 0)
            ?.hourlyWeather
            ?.map { it.toViewData() }
            .orEmpty()
    }

    val dailyWeather = CombinedLiveData(weather, selectedDayOfWeek) { weather, day ->
        weather?.days
            ?.map { it.toViewData(it.dayOfTheWeek == day) }
            ?.sortedBy { it.dayOfTheWeekNumber }.orEmpty()
    }

    init {
        cityId.value = 4047914
    }

    fun onDaySelected(dayNumber: Int) {
        selectedDayOfWeek.value = dayNumber
    }

    private fun Weather.retrieveWeatherDay(dayNumber: Int) =
        days.find { it.dayOfTheWeek == dayNumber}


    private fun handleFailure(failure: ErrorType) {
        showSnackbar(failure.getMessage())
        setLoading(false)
    }

    private fun setLoading(value: Boolean) {
        _isLoading.value = value
    }

    private fun chooseImageDensity(imageURLs: City.ImageURLs) =
        when(DisplayUtils.getScreenDensity()) {
            MDPI, SMALLER -> imageURLs.mdpiImageURL
            HDPI -> imageURLs.hdpiImageURL
            XHDPI, LARGER -> imageURLs.xhdpiImageURL
        }

    sealed class CityScreenEvent
}