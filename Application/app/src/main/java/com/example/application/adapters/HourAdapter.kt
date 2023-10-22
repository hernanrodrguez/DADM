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
import com.example.application.entities.City
import com.example.application.entities.Hour
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class HourAdapter (
    var hourList: MutableList<Hour>
) : RecyclerView.Adapter<HourAdapter.HourHolder>() {

    class HourHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View
        init {
            this.view = v
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun unixToText(unixTime: Long, strDateFormat: String, timezone: String): String {
            val instant = Instant.ofEpochSecond(unixTime)
            val timezoneObj = ZoneId.of(timezone)
            val timezoneDate = ZonedDateTime.ofInstant(instant, timezoneObj)
            val formato = DateTimeFormatter.ofPattern(strDateFormat)
            return timezoneDate.format(formato)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun setHour(time: Number, tz: String){
            var textViewHour: TextView = view.findViewById(R.id.textViewHour)
            textViewHour.text = unixToText(time.toLong(), "HH:mm", tz)
        }
        fun setTempC(tempC: Number){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var textViewTemp: TextView = view.findViewById(R.id.textViewTemp)
            textViewTemp.text = "$tempC°C"
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hour, parent, false)
        return (HourHolder(view))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: HourHolder, i: Int) {
        holder.setHour(hourList[i].timeEpoch, "America/Argentina/Buenos_Aires") // TODO: corregir timezone
        holder.setTempC(hourList[i].tempC)
        holder.setRain(hourList[i].chanceOfRain)
        holder.setWind(hourList[i].windKph)
        holder.setAvatar(hourList[i].condition.icon)

    }

    override fun getItemCount(): Int {
        return hourList.size
    }

}