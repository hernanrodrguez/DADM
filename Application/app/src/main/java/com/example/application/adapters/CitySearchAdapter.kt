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

class CitySearchAdapter (
    var cityList: MutableList<City>,
    var onClick: (Int) -> Unit
) : RecyclerView.Adapter<CitySearchAdapter.CitySearchHolder>() {

    class CitySearchHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View
        init {
            this.view = v
        }
        fun setName(cityName: String, cityCountry: String){
            var textViewCityName: TextView = view.findViewById(R.id.citySearchName)
            var textViewCityCountry: TextView = view.findViewById(R.id.citySearchCountry)
            textViewCityName.text = "$cityName"
            textViewCityCountry.text = "$cityCountry"
        }
        fun getCard(): CardView {
            return view.findViewById(R.id.itemSearchCity)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitySearchHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city_search, parent, false)
        return (CitySearchHolder(view))
    }

    override fun onBindViewHolder(holder: CitySearchHolder, position: Int) {
        holder.setName(cityList[position].name, cityList[position].country)
        holder.getCard().setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

}