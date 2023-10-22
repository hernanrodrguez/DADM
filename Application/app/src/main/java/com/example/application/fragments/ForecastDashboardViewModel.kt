package com.example.application.fragments

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.application.entities.City
import com.example.application.entities.ForecastResponse
import com.example.application.interfaces.CurrentApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecastDashboardViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val forecastList: MutableList<ForecastResponse> = mutableListOf()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getForecasts(): MutableList<ForecastResponse> {
        forecastCity("London")
        forecastCity("Buenos Aires")
        forecastCity("Paris")
        forecastCity("Madrid")

        for (forecast in forecastList) {
            Log.d("forecastList", forecast.location.name)
        }
        return forecastList
    }

    private suspend fun forecastCity(cityName: String) {

        val call = getRetrofit().create(CurrentApi::class.java)
            .getForecast("forecast.json", "cf91e562d2444d5d8e303730231810", cityName, 1, "no", "no", "es")
        val rsp = call.body()
        if (call.isSuccessful) {
            val forecast = ForecastResponse(rsp?.location!!, rsp?.current!!, rsp?.forecast!!)
            Log.d("CoroutineScope", forecast.location.name + " OK")
            Log.d("condition", forecast.forecast.forecastDays[0].day.condition.text)
            forecastList.add(forecast)
        }
    }
}