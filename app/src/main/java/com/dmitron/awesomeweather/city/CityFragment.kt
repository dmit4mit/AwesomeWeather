package com.dmitron.awesomeweather.city

import android.os.Bundle
import androidx.core.os.bundleOf
import com.dmitron.awesomeweather.R
import com.dmitron.awesomeweather.base.BaseFragment
import com.dmitron.awesomeweather.city.daysAdapter.DailyWeatherAdapter
import com.dmitron.awesomeweather.city.hourlyadapter.HourlyWeatherAdapter
import com.dmitron.awesomeweather.databinding.FragmentCityBinding

class CityFragment : BaseFragment<FragmentCityBinding, CityViewModel>(CityViewModel::class) {
    override fun layoutId() = R.layout.fragment_city

    private val hourlyAdapter by lazy { HourlyWeatherAdapter() }
    private val dailyAdapter by lazy { DailyWeatherAdapter(viewModel::onDaySelected) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadCity(requireArguments().getLong(KEY_CITY_ID))
    }

    override fun setupViews() {
        binding.rvHourlyWeather.adapter = hourlyAdapter
        binding.rvDailyWeather.adapter = dailyAdapter
        binding.refreshLayout.setProgressViewOffset(false, 0,
        resources.getDimensionPixelSize(R.dimen.refresh_progress_offset_end))
    }

    override fun observeViewModel(viewModel: CityViewModel) {
        viewModel.hourlyWeather.observe(viewLifecycleOwner) {
            hourlyAdapter.submitList(it)
        }
        viewModel.dailyWeather.observe(viewLifecycleOwner) {
            dailyAdapter.submitList(it)
        }
    }

    companion object {
        private const val KEY_CITY_ID = "KEY_CITY_ID"

        fun newInstance(cityId: Long) = CityFragment().apply {
            arguments = bundleOf(
                KEY_CITY_ID to cityId
            )
        }
    }

}