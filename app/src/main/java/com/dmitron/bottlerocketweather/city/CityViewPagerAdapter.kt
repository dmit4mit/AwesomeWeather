package com.dmitron.bottlerocketweather.city

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CityViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = ArrayList<Pair<Long, Fragment>>()

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position].second

    override fun getItemId(position: Int): Long {
        return fragments[position].first
    }

    override fun containsItem(itemId: Long): Boolean {
        return fragments.map { it.first }.contains(itemId)
    }

    fun addFragment(fragment: Fragment, cityId: Long) {
        if (fragments.find { it.first == cityId } != null) return
        fragments.add(cityId to fragment)
        notifyItemInserted(fragments.lastIndex)
    }

    fun removeByCityId(cityId: Long) {
        val removeIndex = fragments.indexOfFirst { it.first == cityId }
        if (removeIndex != -1) {
            fragments.removeAt(removeIndex)
            notifyItemRemoved(removeIndex)
        }
    }

    fun getCityIdByPosition(position: Int) = fragments[position].first
}