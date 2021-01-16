package com.dmitron.bottlerocketweather.city

import com.dmitron.bottlerocketweather.R
import com.dmitron.bottlerocketweather.base.BaseFragment
import com.dmitron.bottlerocketweather.city.daysAdapter.DailyWeatherAdapter
import com.dmitron.bottlerocketweather.city.hourlyadapter.HourlyWeatherAdapter
import com.dmitron.bottlerocketweather.databinding.FragmentCityBinding

class CityFragment : BaseFragment<FragmentCityBinding, CityViewModel>(CityViewModel::class) {
    override fun layoutId() = R.layout.fragment_city

    private val hourlyAdapter by lazy { HourlyWeatherAdapter() }
    private val dailyAdapter by lazy { DailyWeatherAdapter(viewModel::onDaySelected) }

    override fun setupViews() {
        binding.rvHourlyWeather.adapter = hourlyAdapter
        binding.rvDailyWeather.adapter = dailyAdapter
    }

    override fun observeViewModel(viewModel: CityViewModel) {
        viewModel.hourlyWeather.observe(viewLifecycleOwner) {
            hourlyAdapter.submitList(it)
        }
        viewModel.dailyWeather.observe(viewLifecycleOwner) {
            dailyAdapter.submitList(it)
        }
    }
}