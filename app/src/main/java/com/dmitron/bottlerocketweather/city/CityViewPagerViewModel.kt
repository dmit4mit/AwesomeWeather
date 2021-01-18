package com.dmitron.bottlerocketweather.city

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.dmitron.bottlerocketweather.R
import com.dmitron.bottlerocketweather.base.BaseViewModel
import com.dmitron.bottlerocketweather.main.TopBarClickListener
import com.dmitron.domain.usecases.GetCityIdsUseCase
import com.dmitron.domain.usecases.SaveCityIdsUseCase

class CityViewPagerViewModel(
    private val getCityIdsUseCase: GetCityIdsUseCase,
    private val saveCityIdsUseCase: SaveCityIdsUseCase,
) : BaseViewModel<CityViewPagerScreenEvent>(), LifecycleObserver {
    private val _cityIds = mutableListOf<Long>()
    val cityIds: List<Long> = _cityIds

    var selectedCityId: Long? = null

    val topBarClickHandler = object : TopBarClickListener {
        override fun onSearchClicked() {
            setEvent(CityViewPagerScreenEvent.OpenSearchScreen)
        }

        override fun onTrashClicked() {
            selectedCityId?.let { removeId ->
                if (_cityIds.size > 1) {
                    _cityIds.remove(removeId)
                    setEvent(CityViewPagerScreenEvent.RemoveCity(removeId))
                }
            } 
        }

        override fun onRadarClicked() {
//            TODO("Not yet implemented")
        }

    }

    init {
        val ids = getCityIdsUseCase().ifEmpty { listOf(DEFAULT_CITY_ID) }
        _cityIds.addAll(ids)
    }

    fun onCitySelected(cityId: Long) {
        selectedCityId = cityId
    }

    fun addNewCities(ids: List<Long>) {
        _cityIds.addAll(ids)
        setEvent(CityViewPagerScreenEvent.AddCitiesScreens(ids))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun saveCurrentCities() {
        saveCityIdsUseCase(SaveCityIdsUseCase.Params(_cityIds))
    }

    companion object {
        private const val DEFAULT_CITY_ID: Long = 4047914
    }
}

sealed class CityViewPagerScreenEvent {
    data class AddCitiesScreens(val ids: List<Long>) : CityViewPagerScreenEvent()
    object OpenSearchScreen : CityViewPagerScreenEvent()
    data class RemoveCity(val id: Long) : CityViewPagerScreenEvent()
}