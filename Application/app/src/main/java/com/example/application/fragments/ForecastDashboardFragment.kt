package com.example.application.fragments

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
import kotlinx.coroutines.launch

class ForecastDashboardFragment : Fragment() {

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

        lifecycleScope.launch {
            val forecastsList = viewModel.getForecasts()
            adapter = ForecastAdapter(forecastsList)
            for (forecast in forecastsList) {
                Log.d("lifecycleScope", forecast.location.name)
            }
            recForecast.layoutManager = LinearLayoutManager(context)
            recForecast.adapter = adapter
        }
    }

}