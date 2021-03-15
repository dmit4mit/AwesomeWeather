package com.dmitron.awesomeweather.search

import android.os.Bundle
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import com.dmitron.awesomeweather.R
import com.dmitron.awesomeweather.base.BaseFragment
import com.dmitron.awesomeweather.databinding.FragmentSearchBinding
import com.dmitron.awesomeweather.search.adapter.SearchAdapter
import com.dmitron.awesomeweather.utils.addDividerDecoration
import com.dmitron.awesomeweather.utils.debounce
import com.dmitron.awesomeweather.utils.observeEvent
import com.dmitron.awesomeweather.utils.onChange

class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(SearchViewModel::class) {
    override fun layoutId() = R.layout.fragment_search

    private val adapter by lazy { SearchAdapter(viewModel::onSearchItemClicked) }

    override fun setupViews() {
        setupSearchRecycle()
        setupSearch()
    }

    private fun setupSearch() {
        binding.searchEditText.requestFocus()
        mainUiController.showKeyboard()
        binding.searchEditText.onChange(debounce(lifecycleScope, viewModel::onSearchTextChanged))
    }

    private fun setupSearchRecycle() {
        binding.rvSearch.adapter = adapter
        binding.rvSearch.addDividerDecoration()
    }

    override fun observeViewModel(viewModel: SearchViewModel) {
        viewModel.eventProvider.observeEvent(viewLifecycleOwner) {
            when (it) {
                is SearchScreenEvent.SetSearchResult -> setSearchResult(it.cityId)
                SearchScreenEvent.ShowKeyboard -> mainUiController.showKeyboard()
                SearchScreenEvent.HideKeyboard -> mainUiController.hideKeyboard()
            }
        }
        viewModel.foundCities.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setSearchResult(cityId: Long) {
        Bundle().apply { putLong(BUNDLE_KEY_SEARCH_CITY_ID, cityId) }.also {
            setFragmentResult(FRAGMENT_RESULT_SEARCH_KEY, it)
        }
    }

    companion object {
        const val FRAGMENT_RESULT_SEARCH_KEY = "FRAGMENT_RESULT_SEARCH_KEY"
        const val BUNDLE_KEY_SEARCH_CITY_ID = "BUNDLE_SEARCH_CITY_ID"
    }
}
