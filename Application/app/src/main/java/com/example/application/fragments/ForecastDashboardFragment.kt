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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.application.R
import com.example.application.adapters.CityAdapter
import com.example.application.adapters.ForecastAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class ForecastDashboardFragment : Fragment() {

    private val PREF_NAME = "myPreferences"

    companion object {
        fun newInstance() = ForecastDashboardFragment()
    }

    lateinit var v: View
    private lateinit var recForecast: RecyclerView
    private lateinit var adapter: ForecastAdapter

    private lateinit var viewModel: ForecastDashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_forecast_dashboard, container, false)

        recForecast = v.findViewById(R.id.recForecast)

        return v

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForecastDashboardViewModel::class.java)

        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = sharedPref.getString("myList", "")

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
            }

        }
    }

}