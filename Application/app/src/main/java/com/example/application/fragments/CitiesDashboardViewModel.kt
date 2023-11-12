package com.example.application.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.activities.MainActivity
import com.example.application.entities.City
import com.example.application.interfaces.CurrentApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CitiesDashboardViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getLocation(latitude: Double, longitude: Double): City {
        return currentCity("$latitude,$longitude")
    }

    suspend fun getCities(cities: List<String>): MutableList<City> {
        val citiesList: MutableList<City> = mutableListOf()
        for (city in cities) {
            citiesList.add(currentCity(city))
            Log.d("citiesList", city)
        }
        return citiesList
    }

    private suspend fun currentCity(cityName: String) : City {
        val city = City()

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

}