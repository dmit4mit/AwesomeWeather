package com.dmitron.awesomeweather.search.adapter

import com.dmitron.domain.models.City

data class SearchItem(
    val cityId: Long,
    val name: String,
)

fun City.toSearchItem() = SearchItem(id, name)