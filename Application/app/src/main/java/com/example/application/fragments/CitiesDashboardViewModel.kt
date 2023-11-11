package com.example.application.fragments

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.entities.City
import com.example.application.interfaces.CurrentApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CitiesDashboardViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val citiesList: MutableList<City> = mutableListOf()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getCities(cities: List<String>): MutableList<City> {
        citiesList.clear()

        for (city in cities) {
            currentCity(city)
            Log.d("citiesList", city)
        }
        return citiesList
    }

    private suspend fun currentCity(cityName: String) {
        var city = City()

        val call = getRetrofit().create(CurrentApi::class.java)
            .getCurrent("current.json", "cf91e562d2444d5d8e303730231810", cityName, "es")
        val rsp = call.body()
        if (call.isSuccessful) {
            city.setCurrent(rsp?.location, rsp?.current)
            Log.d("CoroutineScope", city.name + " OK")
            Log.d("condition", city.condition)
            citiesList.add(city)
        }
    }
}