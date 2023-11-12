package com.example.application.fragments

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidplot.xy.CatmullRomInterpolator
import com.androidplot.xy.LineAndPointFormatter
import com.androidplot.xy.PanZoom
import com.androidplot.xy.SimpleXYSeries
import com.androidplot.xy.XYGraphWidget
import com.androidplot.xy.XYPlot
import com.androidplot.xy.XYSeries
import com.bumptech.glide.Glide
import com.example.application.R
import com.example.application.adapters.DayAdapter
import com.example.application.adapters.ForecastAdapter
import com.example.application.adapters.HourAdapter
import com.example.application.entities.City
import com.example.application.entities.ForecastResponse
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.Arrays

class ForecastDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ForecastDetailFragment()
    }

    private lateinit var v: View
    private lateinit var arg : ForecastResponse
    private lateinit var imageViewToday: ImageView
    private lateinit var textViewCityName: TextView
    private lateinit var textViewTodayDescription: TextView
    private lateinit var textViewMinTemp: TextView
    private lateinit var textViewMaxTemp: TextView
    private lateinit var textViewHumidity: TextView
    private lateinit var textViewWind: TextView
    private lateinit var textViewRain: TextView
    private lateinit var textViewSnow: TextView
    private lateinit var plot: XYPlot

    private lateinit var recHours: RecyclerView
    private lateinit var adapterHour: HourAdapter

    private lateinit var recDays: RecyclerView
    private lateinit var adapterDay: DayAdapter

    private lateinit var viewModel: ForecastDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_forecast_detail, container, false)

        textViewCityName = v.findViewById(R.id.textViewCityName)
        textViewTodayDescription = v.findViewById(R.id.textViewTodayDescription)
        textViewMinTemp = v.findViewById(R.id.textViewMinTemp)
        textViewMaxTemp = v.findViewById(R.id.textViewMaxTemp)
        textViewHumidity = v.findViewById(R.id.textViewHumidity)
        textViewWind = v.findViewById(R.id.textViewWind)
        textViewRain = v.findViewById(R.id.textViewRain)
        textViewSnow = v.findViewById(R.id.textViewSnow)
        plot = v.findViewById(R.id.plot)

        imageViewToday = v.findViewById(R.id.imageViewToday)

        recHours = v.findViewById(R.id.recHours)
        recDays = v.findViewById(R.id.recDays)



        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForecastDetailViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        arg = ForecastDetailFragmentArgs.fromBundle(requireArguments()).forecast

        textViewCityName.text = arg.location.name + ", " + arg.location.country
        textViewTodayDescription.text = arg.current.condition.text
        textViewMinTemp.text = arg.forecast.forecastDays[0].day.minTempC.toString() +  "°C"
        textViewMaxTemp.text = arg.forecast.forecastDays[0].day.maxTempC.toString() +  "°C"
        textViewHumidity.text = arg.forecast.forecastDays[0].day.avgHumidity.toString() + "%"
        textViewWind.text = arg.forecast.forecastDays[0].day.maxWindKph.toString() + "°C"
        textViewRain.text = arg.forecast.forecastDays[0].day.dailyChanceOfRain.toString() + "%"
        textViewSnow.text = arg.forecast.forecastDays[0].day.dailyChanceOfSnow.toString() + "%"

        adapterHour = HourAdapter(arg.forecast.forecastDays[0].hour)
        adapterDay = DayAdapter(arg.forecast.forecastDays)

        recHours.layoutManager = LinearLayoutManager(context)
        recHours.adapter = adapterHour

        recDays.layoutManager = LinearLayoutManager(context)
        recDays.adapter = adapterDay

        Glide.with(v).load("https:" + arg.current.condition.icon.replace("64x64", "128x128")).into(imageViewToday)

        viewModel.setUpTempPlot(arg, plot)

    }

}