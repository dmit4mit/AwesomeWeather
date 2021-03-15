package com.dmitron.awesomeweather.di

import com.dmitron.awesomeweather.city.CityViewModel
import com.dmitron.awesomeweather.city.CityViewPagerViewModel
import com.dmitron.awesomeweather.main.MainViewModel
import com.dmitron.awesomeweather.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { CityViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { CityViewPagerViewModel(get(), get()) }
}