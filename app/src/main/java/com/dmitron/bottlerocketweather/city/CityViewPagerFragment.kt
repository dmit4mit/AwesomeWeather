package com.dmitron.bottlerocketweather.city

import android.os.Bundle
import androidx.fragment.app.setFragmentResultListener
import com.dmitron.bottlerocketweather.R
import com.dmitron.bottlerocketweather.base.BaseFragment
import com.dmitron.bottlerocketweather.databinding.FragmentCityViewPagerBinding
import com.dmitron.bottlerocketweather.search.SearchFragment
import com.dmitron.bottlerocketweather.utils.observeEvent
import com.dmitron.bottlerocketweather.utils.onPageSelected
import com.google.android.material.tabs.TabLayoutMediator

class CityViewPagerFragment :
    BaseFragment<FragmentCityViewPagerBinding, CityViewPagerViewModel>(CityViewPagerViewModel::class) {
    override fun layoutId() = R.layout.fragment_city_view_pager
    private lateinit var adapter: CityViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        setFragmentResultListener(SearchFragment.FRAGMENT_RESULT_SEARCH_KEY) { _, bundle ->
            val cityId = bundle.getLong(SearchFragment.BUNDLE_KEY_SEARCH_CITY_ID)
            viewModel.addNewCities(listOf(cityId))
        }
    }

    override fun setupViews() {
        setupViewPager()
    }

    override fun observeViewModel(viewModel: CityViewPagerViewModel) {
        viewModel.eventProvider.observeEvent(viewLifecycleOwner) {
            when(it) {
                is CityViewPagerScreenEvent.AddCitiesScreens -> addCitiesToViewPager(it.ids)
                CityViewPagerScreenEvent.OpenSearchScreen -> openSearchFragment()
                is CityViewPagerScreenEvent.RemoveCity -> removeCityFromViewPager(it.id)
            }
        }
    }

    private fun removeCityFromViewPager(id: Long) {
        adapter.removeByCityId(id)
    }

    private fun openSearchFragment() {
        navigate(
            CityViewPagerFragmentDirections.actionCityViewPagerFragmentDestinationToSearchFragment()
        )
    }

    private fun addCitiesToViewPager(ids: List<Long>) {
        ids.forEach {
            val fragment = CityFragment.newInstance(it)
            adapter.addFragment(fragment, it)
        }
    }

    private fun setupViewPager() {
        adapter = CityViewPagerAdapter(this)
        addCitiesToViewPager(viewModel.cityIds)
        binding.viewPager.onPageSelected {
            viewModel.onCitySelected(adapter.getCityIdByPosition(it))
        }
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()
    }
}