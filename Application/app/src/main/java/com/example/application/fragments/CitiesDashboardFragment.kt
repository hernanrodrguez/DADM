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
import com.example.application.adapters.TeamAdapter
import com.example.application.entities.City
import com.example.application.entities.Team
import com.example.application.interfaces.CurrentApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitiesDashboardFragment : Fragment() {

    companion object {
        fun newInstance() = CitiesDashboardFragment()
    }

    lateinit var v: View
    private lateinit var recCities: RecyclerView
    private lateinit var adapter: CityAdapter
    private lateinit var fab: FloatingActionButton

    private lateinit var viewModel: CitiesDashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_cities_dashboard, container, false)

        recCities = v.findViewById(R.id.recCities)
        fab = v.findViewById(R.id.fab)

        fab.setOnClickListener {
            val action =
                CitiesDashboardFragmentDirections.actionCitiesDashboardFragmentToAddCityFragment()
            findNavController().navigate(action)
        }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CitiesDashboardViewModel::class.java)

        lifecycleScope.launch {
            val citiesList = viewModel.getCities()
            adapter = CityAdapter(
                citiesList
            ) {
                val action =
                    CitiesDashboardFragmentDirections.actionCitiesDashboardFragmentToCityCurrentDetailFragment(
                        citiesList[it]
                    )
                findNavController().navigate(action)
            }
            for (city in citiesList) {
                Log.d("lifecycleScope", city.name)
            }
            recCities.layoutManager = LinearLayoutManager(context)
            recCities.adapter = adapter
        }

    }

}