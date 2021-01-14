package com.dmitron.bottlerocketweather.city

import com.dmitron.bottlerocketweather.R
import com.dmitron.bottlerocketweather.base.BaseFragment
import com.dmitron.bottlerocketweather.databinding.FragmentCityBinding

class CityFragment : BaseFragment<FragmentCityBinding, CityViewModel>(CityViewModel::class) {
    override fun layoutId() = R.layout.fragment_city
}