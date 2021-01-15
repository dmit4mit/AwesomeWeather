package com.dmitron.bottlerocketweather.di

import com.dmitron.bottlerocketweather.city.CityViewModel
import com.dmitron.bottlerocketweather.main.MainViewModel
import com.dmitron.bottlerocketweather.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { CityViewModel() }
    viewModel { SearchViewModel() }
}