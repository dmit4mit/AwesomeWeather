package com.dmitron.bottlerocketweather.main

interface MainUiController {
    fun setTopBarVisibility(isVisible: Boolean)
    fun setTopBarClickListener(clickListener: TopBarClickListener)
    fun removeTopBarClickListener()
    fun hideKeyboard()
    fun showKeyboard()
}