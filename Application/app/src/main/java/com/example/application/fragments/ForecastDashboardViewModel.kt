package com.example.application.fragments

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.application.entities.City
import com.example.application.entities.ForecastResponse
import com.example.application.interfaces.CurrentApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecastDashboardViewModel : ViewModel() {


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getLocation(latitude: Double, longitude: Double): ForecastResponse? {
        return forecastCity("$latitude,$longitude")
    }

    suspend fun getForecasts(cities: List<String>): MutableList<ForecastResponse> {
        val forecastList: MutableList<ForecastResponse> = mutableListOf()

        for (city in cities) {
            forecastCity(city)?.let { forecastList.add(it) }
            Log.d("forecastList", city)
        }
        return forecastList
    }

    private suspend fun forecastCity(cityName: String): ForecastResponse? {
        val call = getRetrofit().create(CurrentApi::class.java)
            .getForecast("forecast.json", "cf91e562d2444d5d8e303730231810", cityName, 4, "no", "no", "es")
        val rsp = call.body()
        return if (call.isSuccessful) {
            val forecastResponse = ForecastResponse(rsp?.location!!, rsp?.current!!, rsp?.forecast!!)
            Log.d("CoroutineScope", forecastResponse.location.name + " OK")
            Log.d("condition", forecastResponse.forecast.forecastDays[0].day.condition.text)
            forecastResponse
        } else {
            null
        }
    }
}