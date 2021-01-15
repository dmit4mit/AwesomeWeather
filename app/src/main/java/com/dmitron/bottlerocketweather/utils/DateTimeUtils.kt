package com.dmitron.bottlerocketweather.utils

import timber.log.Timber
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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
}
