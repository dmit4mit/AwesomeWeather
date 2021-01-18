package com.dmitron.bottlerocketweather.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.dmitron.bottlerocketweather.base.BaseViewModel
import com.dmitron.bottlerocketweather.city.daysAdapter.toViewData
import com.dmitron.bottlerocketweather.city.hourlyadapter.toViewData
import com.dmitron.bottlerocketweather.main.TopBarClickListener
import com.dmitron.bottlerocketweather.utils.CombinedLiveData
import com.dmitron.bottlerocketweather.utils.DateTimeUtils
import com.dmitron.bottlerocketweather.utils.DisplayUtils
import com.dmitron.bottlerocketweather.utils.DisplayUtils.Density.*
import com.dmitron.domain.models.City
import com.dmitron.domain.models.Weather
import com.dmitron.domain.usecases.GetCityWeatherByIdUseCase
import kotlinx.coroutines.flow.collect

class CityViewModel(
    private val getCityWeatherByIdUseCase: GetCityWeatherByIdUseCase
) : BaseViewModel<CityViewModel.CityScreenEvent>() {
    private val cityId = MutableLiveData<Long>()

    val cityWeather = cityId.switchMap { id ->
        liveData {
            getCityWeatherByIdUseCase(GetCityWeatherByIdUseCase.Params(id)).collect { result ->
                handleResult(result) { emit(it) }
            }
        }
    }

    val city = cityWeather.map { it.city }
    val weather = cityWeather.map { it.weather }

    val name = city.map { "${it.name}, ${it.countryCode}" }
    val date = city.map { DateTimeUtils.formatCurrentDate(it.timezone) }
    val time = city.map { DateTimeUtils.formatCurrentTime(it.timezone) }

    private val selectedDayOfWeek = MutableLiveData(DateTimeUtils.getTodayDayOfWeek())

    // todo: update every hour if needed
    val currentWeather = cityWeather.map { cityWeather ->
        val todayWeather = cityWeather.weather.retrieveWeatherDay(DateTimeUtils.getTodayDayOfWeek())
        val temp = todayWeather?.hourlyWeather?.find {
            it.hour == DateTimeUtils.getCurrentHourIn24Format(cityWeather.city.timezone)
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

    fun onDaySelected(dayNumber: Int) {
        selectedDayOfWeek.value = dayNumber
    }

    fun loadCity(cityId: Long) {
        if (cityId != 0.toLong()) this.cityId.value = cityId
    }

    private fun Weather.retrieveWeatherDay(dayNumber: Int) =
        days.find { it.dayOfTheWeek == dayNumber }

    private fun chooseImageDensity(imageURLs: City.ImageURLs) =
        when (DisplayUtils.getScreenDensity()) {
            MDPI, SMALLER -> imageURLs.mdpiImageURL
            HDPI -> imageURLs.hdpiImageURL
            XHDPI, LARGER -> imageURLs.xhdpiImageURL
        }

    sealed class CityScreenEvent {

    }
}