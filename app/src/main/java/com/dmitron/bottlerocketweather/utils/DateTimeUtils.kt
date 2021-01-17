package com.dmitron.bottlerocketweather.utils

import timber.log.Timber
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

object DateTimeUtils {
    fun formatCurrentDate(timezone: String): String =
        try {
            val formatter =
                DateTimeFormatter.ofPattern("EEE M/d/yy").withZone(ZoneId.of(timezone))
            LocalDate.now().format(formatter)
        } catch (e: Exception) {
            Timber.e(e, "Failed formatting date")
            ""
        }

    fun formatCurrentTime(timezone: String): String =
        try {
            val formatter =
                DateTimeFormatter
                    .ofPattern("KK:mm a")
                    .withZone(ZoneId.of(timezone))
            LocalTime.now().format(formatter)
        } catch (e: Exception) {
            Timber.e(e, "Failed formatting date")
            ""
        }

    /**
     * Day in range [0 - 6].
     */
    fun getTodayDayOfWeek(): Int = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1

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
}
