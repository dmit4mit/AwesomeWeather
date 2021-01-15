package com.dmitron.bottlerocketweather.search

import com.dmitron.bottlerocketweather.R
import com.dmitron.bottlerocketweather.base.BaseFragment
import com.dmitron.bottlerocketweather.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(SearchViewModel::class) {
    override fun layoutId() = R.layout.fragment_search
}