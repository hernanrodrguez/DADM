package com.example.application.fragments

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.application.entities.City
import com.example.application.interfaces.CurrentApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class CityCurrentDetailViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getCity(cityName: String): City {
        var city = City()
        val call = getRetrofit().create(CurrentApi::class.java)
            .getCurrent("current.json", "cf91e562d2444d5d8e303730231810", cityName, "es")
        val rsp = call.body()
        if (call.isSuccessful) {
            city.setCurrent(rsp?.location, rsp?.current)
            Log.d("CoroutineScope", city.name + " OK")
            Log.d("condition", city.condition)
        }
        return city
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun unixToText(unixTime: Long, strDateFormat: String, timezone: String): String {
        val instant = Instant.ofEpochSecond(unixTime)
        val timezoneObj = ZoneId.of(timezone)
        val timezoneDate = ZonedDateTime.ofInstant(instant, timezoneObj)
        val formato = DateTimeFormatter.ofPattern(strDateFormat)
        return timezoneDate.format(formato)
    }
}