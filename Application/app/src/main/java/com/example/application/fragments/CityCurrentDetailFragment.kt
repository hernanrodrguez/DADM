package com.example.application.fragments

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.application.R
import com.example.application.entities.City
import com.example.application.entities.Team

class CityCurrentDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CityCurrentDetailFragment()
    }

    private lateinit var v : View
    private lateinit var arg : City
    private lateinit var textViewCityName : TextView
    private lateinit var textViewDescription : TextView
    private lateinit var textViewCurrentTempC : TextView
    private lateinit var textViewtvCurrentHumidity : TextView
    private lateinit var textViewFeelsLike : TextView
    private lateinit var textViewWind : TextView
    private lateinit var textViewPressure : TextView
    private lateinit var textViewPrecipitation : TextView
    private lateinit var textViewClouds : TextView
    private lateinit var textViewUV : TextView
    private lateinit var textViewLastUpdate : TextView
    private lateinit var textViewLocalTime : TextView
    private lateinit var imageViewCurrent: ImageView

    private lateinit var viewModel: CityCurrentDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_city_current_detail, container, false)

        textViewCityName = v.findViewById(R.id.textViewCityName)
        textViewDescription = v.findViewById(R.id.textViewDescription)
        textViewCurrentTempC = v.findViewById(R.id.textViewCurrentTempC)
        textViewtvCurrentHumidity = v.findViewById(R.id.textViewtvCurrentHumidity)
        textViewFeelsLike = v.findViewById(R.id.textViewFeelsLike)
        textViewWind = v.findViewById(R.id.textViewWind)
        textViewPressure = v.findViewById(R.id.textViewPressure)
        textViewPrecipitation = v.findViewById(R.id.textViewPrecipitation)
        textViewClouds = v.findViewById(R.id.textViewClouds)
        textViewUV = v.findViewById(R.id.textViewUV)
        textViewLastUpdate = v.findViewById(R.id.textViewLastUpdate)
        textViewLocalTime = v.findViewById(R.id.textViewLocalTime)

        imageViewCurrent = v.findViewById(R.id.imageViewCurrent)

        return v

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CityCurrentDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        arg = CityCurrentDetailFragmentArgs.fromBundle(requireArguments()).city

        textViewCityName.text = arg.name + ", " + arg.country
        textViewDescription.text = arg.condition
        textViewCurrentTempC.text = arg.tempC.toString() + "°C"
        textViewtvCurrentHumidity.text = arg.humidity.toString() + "%"
        textViewFeelsLike.text = arg.feelsLike.toString() + "°C"
        textViewWind.text = "Viento: " + arg.windKph.toString() + " km/h"
        textViewPressure.text = "Presión: " + arg.pressure.toString() + " mbar"
        textViewPrecipitation.text = "Precipitación: " + arg.precipitation.toString() + " mm"
        textViewClouds.text = "Cobertura de nubes: " + arg.cloudCover.toString() + "%"
        textViewUV.text = "Índice UV: " + arg.uvIndex.toString()
        textViewLastUpdate.text = "Actualizado: " + viewModel.unixToText(arg.lastUpdated.toLong(), "HH:mm", arg.tz)
        textViewLocalTime.text = "Hora local: " + viewModel.unixToText(arg.localTime.toLong(), "HH:mm", arg.tz)
        Glide.with(v).load("https:" + arg.conditionImgUrl.replace("64x64", "128x128")).into(imageViewCurrent)
    }

}