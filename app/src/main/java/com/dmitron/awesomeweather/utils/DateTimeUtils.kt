package com.dmitron.awesomeweather.utils

import timber.log.Timber
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

object DateTimeUtils {
    fun formatCurrentDate(timezone: String): String {
        val formatter =
            DateTimeFormatter.ofPattern("EEE M/d/yy").withZone(getZoneIdFromString(timezone))
        return LocalDate.now().format(formatter)
    }

    /**
     * Day in range [0 - 6], Mon - Sun.
     */
    fun getTodayDayOfWeek(timezone: String): Int {
        val day = Calendar.getInstance(TimeZone.getTimeZone(getZoneIdFromString(timezone)))
            .get(Calendar.DAY_OF_WEEK) - 2
        return if (day == -1) day + 7 else day
    }


    fun getCurrentHourIn24Format(timezone: String): Int =
        try {
            ZonedDateTime.now(ZoneId.of(timezone)).hour
        } catch (e: Exception) {
            Timber.e(e, "Failed formatting time for timezone: $timezone")
            0
        }

    fun format24HourTo12(hour: Int): String {
        return when {
            hour <= 11 -> "${hour + 1}AM"
            else -> "${hour - 11}PM"
        }
    }

    /**
     * @param dayOfWeek The value in range [0..6]
     */
    fun formatDayOfWeekToString(dayOfWeek: Int): String {
        return DayOfWeek.of(dayOfWeek + 1).getDisplayName(TextStyle.SHORT, Locale.getDefault())
    }

    private fun getZoneIdFromString(timezone: String): ZoneId =
        try {
            ZoneId.of(timezone)
        } catch (e: Exception) {
            ZoneId.systemDefault()
        }
}
