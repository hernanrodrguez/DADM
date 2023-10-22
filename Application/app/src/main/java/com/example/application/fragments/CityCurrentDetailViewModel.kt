package com.example.application.fragments

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class CityCurrentDetailViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    fun unixToText(unixTime: Long, strDateFormat: String, timezone: String): String {
        val instant = Instant.ofEpochSecond(unixTime)
        val timezoneObj = ZoneId.of(timezone)
        val timezoneDate = ZonedDateTime.ofInstant(instant, timezoneObj)
        val formato = DateTimeFormatter.ofPattern(strDateFormat)
        return timezoneDate.format(formato)
    }
}