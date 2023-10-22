package com.example.application.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.application.R
import com.example.application.entities.ForecastDay
import com.example.application.entities.Hour
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class DayAdapter(
    var daysList: MutableList<ForecastDay>
) : RecyclerView.Adapter<DayAdapter.DayHolder>() {

    class DayHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View
        init {
            this.view = v
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun dayOfWeek(unixTime: Long): String {
            val d = Date(unixTime * 1000L)
            val f = SimpleDateFormat("EEEE", Locale.getDefault())
            return f.format(d)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun setDay(time: Number){
            var textViewDay: TextView = view.findViewById(R.id.textViewDay)
            textViewDay.text = dayOfWeek(time.toLong())
        }
        fun setTempMinMax(tempMin: Number, tempMax: Number){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var textViewTempMinMax: TextView = view.findViewById(R.id.textViewTempMinMax)
            textViewTempMinMax.text = "$tempMin°C/$tempMax°C"
        }
        fun setRain(rain: Number){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var textViewRain: TextView = view.findViewById(R.id.textViewRain)
            textViewRain.text = "$rain%"
        }
        fun setWind(wind: Number){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var textViewWind: TextView = view.findViewById(R.id.textViewWind)
            textViewWind.text = "$wind km/h"
        }
        fun setAvatar(url: String){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var imageViewForecast: ImageView = view.findViewById(R.id.imageViewForecast)
            Glide.with(view).load("https:" + url.replace("64x64", "128x128")).into(imageViewForecast)
        }

        fun getCard(): CardView {
            return view.findViewById(R.id.cardHour)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        return (DayHolder(view))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DayHolder, i: Int) {
        holder.setDay(daysList[i].dateEpoch)
        holder.setTempMinMax(daysList[i].day.minTempC, daysList[i].day.maxTempC)
        holder.setRain(daysList[i].day.dailyChanceOfRain)
        holder.setWind(daysList[i].day.maxWindKph)
        holder.setAvatar(daysList[i].day.condition.icon)

    }

    override fun getItemCount(): Int {
        return daysList.size
    }

}