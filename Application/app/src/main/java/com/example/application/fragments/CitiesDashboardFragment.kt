package com.example.application.fragments

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.application.R
import com.example.application.activities.MainActivity
import com.example.application.adapters.CityAdapter
import com.example.application.adapters.TeamAdapter
import com.example.application.entities.City
import com.example.application.entities.Team
import com.example.application.interfaces.CurrentApi
import com.google.android.gms.maps.model.LatLng
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
    private lateinit var userid: String
    var latitude: Double? = null
    var longitude: Double? = null
    private lateinit var recCities: RecyclerView
    private lateinit var adapter: CityAdapter
    private lateinit var fab: FloatingActionButton
    private lateinit var progressBar: ProgressBar
    private lateinit var llFragCitDash: LinearLayout

    private lateinit var itemCity: CardView

    private lateinit var viewModel: CitiesDashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_cities_dashboard, container, false)
        
        recCities = v.findViewById(R.id.recCities)
        fab = v.findViewById(R.id.fab)
        progressBar = v.findViewById(R.id.progressBar)
        llFragCitDash = v.findViewById(R.id.llFragCitDash)
        itemCity = v.findViewById(R.id.cvLocation)

        fab.setOnClickListener {
            val action =
                CitiesDashboardFragmentDirections.actionCitiesDashboardFragmentToAddCityFragment()
            findNavController().navigate(action)
        }

        userid = (activity as MainActivity).getUser()

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CitiesDashboardViewModel::class.java)

        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        //sharedPref.edit().clear().apply()
        val json = sharedPref.getString(userid, "")

        val citiesList : MutableList<String> = if (json.isNullOrEmpty()) {
            mutableListOf()
        } else {
            Gson().fromJson(json, object : TypeToken<MutableList<String>>() {}.type)
        }

        (activity as MainActivity).getLastLocation()

        lifecycleScope.launch {
            showProgressBar()
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

                val locationCity: City
                val (latitude, longitude) = (activity as MainActivity).readLastLocation()
                if(latitude != null && longitude != null){
                    locationCity = viewModel.getLocation(latitude, longitude)
                    setLocationCard(locationCity)
                } else {
                    Log.d("LOCATION", "No pude acceder a la ubicacion")
                }
            } else {
                val locationCity: City
                val (latitude, longitude) = (activity as MainActivity).readLastLocation()
                if(latitude != null && longitude != null){
                    locationCity = viewModel.getLocation(latitude, longitude)
                    setLocationCard(locationCity)
                } else {
                    Log.d("LOCATION", "No pude acceder a la ubicacion")
                }
            }
            hideProgressBar()
        }
    }

    private fun setLocationCard(city: City){
        val imageViewCurrent: ImageView = itemCity.findViewById(R.id.imageViewCurrent)
        val textViewDescription: TextView = itemCity.findViewById(R.id.textViewDescription)
        val textViewTempC: TextView = itemCity.findViewById(R.id.textViewTempC)
        val textViewHumFeel: TextView = itemCity.findViewById(R.id.textViewHumFeel)
        val textViewCityName: TextView = itemCity.findViewById(R.id.textViewCityName)

        Glide.with(v).load("https:" + city.conditionImgUrl.replace("64x64", "128x128")).into(imageViewCurrent)
        textViewDescription.text = city.condition
        textViewTempC.text = "${city.tempC}°C"
        textViewHumFeel.text = "H: ${city.humidity} - ST: ${city.feelsLike}°C"
        textViewCityName.text = "${city.name}, ${city.country}"

        itemCity.setOnClickListener {
            val action =
                CitiesDashboardFragmentDirections.actionCitiesDashboardFragmentToCityCurrentDetailFragment(
                    city
                )
            findNavController().navigate(action)
        }
    }

    private fun showProgressBar() {
        llFragCitDash.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
        llFragCitDash.visibility = View.VISIBLE
    }
}