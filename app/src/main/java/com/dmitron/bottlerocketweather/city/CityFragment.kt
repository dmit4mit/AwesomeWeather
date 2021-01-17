package com.dmitron.bottlerocketweather.city

import android.os.Bundle
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.dmitron.bottlerocketweather.R
import com.dmitron.bottlerocketweather.base.BaseFragment
import com.dmitron.bottlerocketweather.city.daysAdapter.DailyWeatherAdapter
import com.dmitron.bottlerocketweather.city.hourlyadapter.HourlyWeatherAdapter
import com.dmitron.bottlerocketweather.databinding.FragmentCityBinding
import com.dmitron.bottlerocketweather.search.SearchFragment
import com.dmitron.bottlerocketweather.utils.observeEvent

class CityFragment : BaseFragment<FragmentCityBinding, CityViewModel>(CityViewModel::class) {
    override fun layoutId() = R.layout.fragment_city

    private val hourlyAdapter by lazy { HourlyWeatherAdapter() }
    private val dailyAdapter by lazy { DailyWeatherAdapter(viewModel::onDaySelected) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(SearchFragment.FRAGMENT_RESULT_SEARCH_KEY) { _, bundle ->
            val cityId = bundle.getLong(SearchFragment.BUNDLE_KEY_SEARCH_CITY_ID)
            viewModel.loadCity(cityId)
        }
    }

    override fun setupViews() {
        mainUiController.setTopBarClickListener(viewModel.topBarClickHandler)
        binding.rvHourlyWeather.adapter = hourlyAdapter
        binding.rvDailyWeather.adapter = dailyAdapter
    }

    override fun observeViewModel(viewModel: CityViewModel) {
        viewModel.eventProvider.observeEvent(viewLifecycleOwner) {
            when(it) {
                CityViewModel.CityScreenEvent.OpenSearchScreen -> openSearchFragment()
            }
        }
        viewModel.hourlyWeather.observe(viewLifecycleOwner) {
            hourlyAdapter.submitList(it)
        }
        viewModel.dailyWeather.observe(viewLifecycleOwner) {
            dailyAdapter.submitList(it)
        }
    }

    private fun openSearchFragment() {
        navigate(CityFragmentDirections.actionCityFragmentToSearchFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainUiController.removeTopBarClickListener()
    }
}