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

class CityAdapter (
    var cityList: MutableList<City>,
    var onClick: (Int) -> Unit
) : RecyclerView.Adapter<CityAdapter.CityHolder>(){

    class CityHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View
        init {
            this.view = v
        }
        fun setName(cityName: String, cityCountry: String){
            var textViewCityName: TextView = view.findViewById(R.id.textViewCityName)
            textViewCityName.text = "$cityName, $cityCountry"
        }
        fun setTempC(tempC: Number){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var textViewTempC: TextView = view.findViewById(R.id.textViewTempC)
            textViewTempC.text = "$tempC°C"
        }
        fun setHumFeel(humidity: Number, feelsLike: Number){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var textViewHumFeel: TextView = view.findViewById(R.id.textViewHumFeel)
            textViewHumFeel.text = "H: $humidity - ST: $feelsLike°C"
        }
        fun setDescription(description: String){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var textViewDescription: TextView = view.findViewById(R.id.textViewDescription)
            textViewDescription.text = description
        }
        fun setAvatar(url: String){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var imageViewCurrent: ImageView = view.findViewById(R.id.imageViewCurrent)
            Glide.with(view).load("https:" + url.replace("64x64", "128x128")).into(imageViewCurrent)
        }

        fun getCard(): CardView {
            return view.findViewById(R.id.cardCity)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return (CityHolder(view))
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.setName(cityList[position].name, cityList[position].country)
        holder.setTempC(cityList[position].tempC)
        holder.setHumFeel(cityList[position].humidity, cityList[position].feelsLike)
        holder.setAvatar(cityList[position].conditionImgUrl)
        holder.setDescription(cityList[position].condition)
        holder.getCard().setOnClickListener {
            onClick(position)
        }
        /*holder.getCard().setOnLongClickListener {
            onLongClick(position)
            true
        }*/
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

}