package com.example.application.fragments

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.application.entities.City
import com.example.application.interfaces.CurrentApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddCityViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val citiesList: MutableList<City> = mutableListOf()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun searchCities(hint: String) : MutableList<City> {
        searchHint(hint)
        for (city in citiesList) {
            Log.d("citiesList", city.name)
        }
        return citiesList
    }

    private suspend fun searchHint(hint: String) {
        val call = getRetrofit().create(CurrentApi::class.java)
            .getSearch("search.json", "cf91e562d2444d5d8e303730231810", hint, "es")
        val rsp = call.body()
        if (call.isSuccessful && rsp != null) {
            for(searchCity in rsp) {
                var city = City()
                city.setSearch(searchCity)
                Log.d("CoroutineScope", city.name + " OK")
                citiesList.add(city)
            }
        }
    }
}