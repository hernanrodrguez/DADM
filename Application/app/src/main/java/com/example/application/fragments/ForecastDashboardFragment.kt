package com.example.application.fragments

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.application.R
import com.example.application.activities.MainActivity
import com.example.application.adapters.CityAdapter
import com.example.application.adapters.ForecastAdapter
import com.example.application.entities.City
import com.example.application.entities.ForecastResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class ForecastDashboardFragment : Fragment() {

    private val PREF_NAME = "myPreferences"

    companion object {
        fun newInstance() = ForecastDashboardFragment()
    }

    lateinit var v: View
    private lateinit var userid: String
    private lateinit var recForecast: RecyclerView
    private lateinit var adapter: ForecastAdapter
    private lateinit var itemForecast: CardView

    private lateinit var viewModel: ForecastDashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_forecast_dashboard, container, false)

        userid = (activity as MainActivity).getUser()

        recForecast = v.findViewById(R.id.recForecast)
        itemForecast = v.findViewById(R.id.cvLocationForecast)

        return v

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForecastDashboardViewModel::class.java)

        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = sharedPref.getString(userid, "")

        val citiesList : MutableList<String> = if (json.isNullOrEmpty()) {
            mutableListOf()
        } else {
            Gson().fromJson(json, object : TypeToken<MutableList<String>>() {}.type)
        }

        lifecycleScope.launch {
            Log.d("LIST SIZE", citiesList.size.toString())
            if(citiesList.size > 0) {
                val forecastsList = viewModel.getForecasts(citiesList)
                adapter = ForecastAdapter(
                    forecastsList
                ) {
                    val action =
                        ForecastDashboardFragmentDirections.actionForecastDashboardFragmentToForecastDetailFragment(
                            forecastsList[it]
                        )
                    findNavController().navigate(action)
                }
                for (forecast in forecastsList) {
                    Log.d("lifecycleScope", forecast.location.name)
                }
                recForecast.layoutManager = LinearLayoutManager(context)
                recForecast.adapter = adapter

                val locationForecast: ForecastResponse?
                val (latitude, longitude) = (activity as MainActivity).readLastLocation()
                if(latitude != null && longitude != null){
                    locationForecast = viewModel.getLocation(latitude, longitude)
                    if(locationForecast != null) {
                        setLocationCard(locationForecast)
                    }
                } else {
                    Log.d("LOCATION", "No pude acceder a la ubicacion")
                }
            } else {
                val locationForecast: ForecastResponse?
                val (latitude, longitude) = (activity as MainActivity).readLastLocation()
                if (latitude != null && longitude != null) {
                    locationForecast = viewModel.getLocation(latitude, longitude)
                    if (locationForecast != null) {
                        setLocationCard(locationForecast)
                    }
                } else {
                    Log.d("LOCATION", "No pude acceder a la ubicacion")
                }
            }
        }
    }

    private fun setLocationCard(forecast: ForecastResponse){
        val imageViewForecast: ImageView = itemForecast.findViewById(R.id.imageViewForecast)
        val textViewForecastDesc: TextView = itemForecast.findViewById(R.id.textViewForecastDesc)
        val textViewTempAvg: TextView = itemForecast.findViewById(R.id.textViewTempAvg)
        val textViewTempMinMax: TextView = itemForecast.findViewById(R.id.textViewTempMinMax)
        val textViewCityName: TextView = itemForecast.findViewById(R.id.textViewCityName)
        val textViewRain: TextView = itemForecast.findViewById(R.id.textViewRain)
        val textViewSnow: TextView = itemForecast.findViewById(R.id.textViewSnow)

        Glide.with(v).load("https:" + forecast.forecast.forecastDays[0].day.condition.icon.replace("64x64", "128x128")).into(imageViewForecast)
        textViewForecastDesc.text = forecast.forecast.forecastDays[0].day.condition.text
        textViewTempAvg.text = "${forecast.forecast.forecastDays[0].day.avgTempC}°C"
        textViewTempMinMax.text = "${forecast.forecast.forecastDays[0].day.minTempC}°C/${forecast.forecast.forecastDays[0].day.maxTempC}°C"
        textViewCityName.text = "${forecast.location.name}"
        textViewRain.text = forecast.forecast.forecastDays[0].day.dailyChanceOfRain.toString() + "%"
        textViewSnow.text = forecast.forecast.forecastDays[0].day.dailyChanceOfSnow.toString() + "%"

        itemForecast.setOnClickListener {
            val action =
                ForecastDashboardFragmentDirections.actionForecastDashboardFragmentToForecastDetailFragment(
                    forecast
                )
            findNavController().navigate(action)
        }

    }

}