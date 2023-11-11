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
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.application.R
import com.example.application.adapters.CityAdapter
import com.example.application.adapters.TeamAdapter
import com.example.application.entities.City
import com.example.application.entities.Team
import com.example.application.interfaces.CurrentApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitiesDashboardFragment : Fragment() {

    private val PREF_NAME = "myPreferences"

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

        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        //sharedPref.edit().clear().apply()

        val json = sharedPref.getString("myList", "")

        val citiesList : MutableList<String> = if (json.isNullOrEmpty()) {
            mutableListOf()
        } else {
            Gson().fromJson(json, object : TypeToken<MutableList<String>>() {}.type)
        }

        lifecycleScope.launch {

            Log.d("LIST SIZE", citiesList.size.toString())
            if(citiesList.size > 0) {
                val cities = viewModel.getCities(citiesList)
                adapter = CityAdapter(
                    cities
                ) {
                    val action =
                        CitiesDashboardFragmentDirections.actionCitiesDashboardFragmentToCityCurrentDetailFragment(
                            cities[it]
                        )
                    findNavController().navigate(action)
                }
                for (city in cities) {
                    Log.d("lifecycleScope", city.name)
                }

            recCities.layoutManager = LinearLayoutManager(context)
            recCities.adapter = adapter
            }
        }

    }

}