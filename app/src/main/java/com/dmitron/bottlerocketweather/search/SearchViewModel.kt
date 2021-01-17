package com.dmitron.bottlerocketweather.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.dmitron.bottlerocketweather.base.BaseViewModel
import com.dmitron.bottlerocketweather.search.adapter.SearchItem
import com.dmitron.bottlerocketweather.search.adapter.toSearchItem
import com.dmitron.domain.usecases.SearchCitiesUseCase
import kotlinx.coroutines.flow.collect

class SearchViewModel(
    private val searchCitiesUseCase: SearchCitiesUseCase
) : BaseViewModel<SearchScreenEvent>() {

    private val query = MutableLiveData("")

    val foundCities = query.switchMap { query ->
        liveData {
            searchCitiesUseCase(SearchCitiesUseCase.Params(query)).collect { result ->
                handleResult(result) { cities ->
                    emit(cities.map { it.toSearchItem() })
                }
            }
        }
    }

    fun onSearchTextChanged(text: String) {
        query.value = text
    }

    fun onSearchItemClicked(searchItem: SearchItem) {
        setEvent(SearchScreenEvent.SetSearchResult(searchItem.cityId))
        setEvent(SearchScreenEvent.HideKeyboard)
        navigateBack()
    }

    fun onCloseClicked() {
        setEvent(SearchScreenEvent.HideKeyboard)
        navigateBack()
    }
}

sealed class SearchScreenEvent {
    data class SetSearchResult(val cityId: Long) : SearchScreenEvent()
    object ShowKeyboard : SearchScreenEvent()
    object HideKeyboard : SearchScreenEvent()
}