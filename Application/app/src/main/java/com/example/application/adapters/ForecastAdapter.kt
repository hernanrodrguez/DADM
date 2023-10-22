package com.example.application.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.application.R
import com.example.application.entities.City
import com.example.application.entities.ForecastResponse

class ForecastAdapter (
    var forecastList: MutableList<ForecastResponse>,
    var onClick: (Int) -> Unit
) : RecyclerView.Adapter<ForecastAdapter.ForecastHolder>() {

    class ForecastHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View
        init {
            this.view = v
        }
        fun setName(cityName: String){
            var textViewCityName: TextView = view.findViewById(R.id.textViewCityName)
            textViewCityName.text = "$cityName"
        }
        fun setTempAvg(tempC: Number){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var textViewTempAvg: TextView = view.findViewById(R.id.textViewTempAvg)
            textViewTempAvg.text = "$tempC°C"
        }
        fun setTempMinMax(tempMin: Number, tempMax: Number){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var textViewTempMinMax: TextView = view.findViewById(R.id.textViewTempMinMax)
            textViewTempMinMax.text = "$tempMin°C/$tempMax°C"
        }
        fun setDescription(description: String){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var textViewForecastDesc: TextView = view.findViewById(R.id.textViewForecastDesc)
            textViewForecastDesc.text = description
        }
        fun setRain(rain: Number){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var textViewRain: TextView = view.findViewById(R.id.textViewRain)
            textViewRain.text = rain.toString() + "%"
        }
        fun setSnow(snow: Number){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var textViewSnow: TextView = view.findViewById(R.id.textViewSnow)
            textViewSnow.text = snow.toString() + "%"
        }
        fun setAvatar(url: String){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var imageViewForecast: ImageView = view.findViewById(R.id.imageViewForecast)
            Glide.with(view).load("https:" + url.replace("64x64", "128x128")).into(imageViewForecast)
        }

        fun getCard(): CardView {
            return view.findViewById(R.id.cardForecast)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return (ForecastHolder(view))
    }

    override fun onBindViewHolder(holder: ForecastHolder, i: Int) {
        holder.setName(forecastList[i].location.name)
        holder.setTempAvg(forecastList[i].forecast.forecastDays[0].day.avgTempC)
        holder.setTempMinMax(forecastList[i].forecast.forecastDays[0].day.minTempC, forecastList[i].forecast.forecastDays[0].day.maxTempC)
        holder.setAvatar(forecastList[i].forecast.forecastDays[0].day.condition.icon)
        holder.setDescription(forecastList[i].forecast.forecastDays[0].day.condition.text)
        holder.setRain(forecastList[i].forecast.forecastDays[0].day.dailyChanceOfRain)
        holder.setSnow(forecastList[i].forecast.forecastDays[0].day.dailyChanceOfSnow)
        holder.getCard().setOnClickListener {
            onClick(i)
        }
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }
}