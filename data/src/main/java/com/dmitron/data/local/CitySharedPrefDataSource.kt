package com.dmitron.data.local

import android.content.SharedPreferences
import com.dmitron.data.utils.string
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class CitySharedPrefDataSource : KoinComponent {
    private val sharedPrefs: SharedPreferences by inject {
        parametersOf(PREF_NAME_CITY_ID)
    }

    private var cityIdsPref by sharedPrefs.string(PREF_CITY_IDS)

    fun saveCityIds(cityIds: List<Long>) {
        cityIdsPref = cityIds.joinToString(DELIMITER)
    }

    fun getCityIds(): List<Long> {
        val ids = cityIdsPref
        if (ids.isEmpty()) return listOf()
        return ids.split(DELIMITER).map { it.toLong() }
    }

    companion object {
        private const val PREF_NAME_CITY_ID = "PREF_NAME_CITY_ID"
        private const val PREF_CITY_IDS = "PREF_CITY_IDS"

        private const val DELIMITER = ","
    }
}