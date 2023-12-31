package com.example.application.fragments

import android.app.AlertDialog
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.application.R
import com.example.application.entities.City
import com.example.application.interfaces.CurrentApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddCityViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val currentHint = MutableLiveData<String>()
    var viewState : MutableLiveData<String> = MutableLiveData()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun searchCities(hint: String) : MutableList<City> {
        val retList = searchHint(hint)
        for (city in retList) {
            Log.d("retList", city.name)
        }
        return retList
    }

    private suspend fun searchHint(hint: String) : MutableList<City> {
        val citiesList: MutableList<City> = mutableListOf()
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
        return citiesList
    }
}