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
import com.example.application.entities.Team

class TeamAdapter (
    var teamList: MutableList<Team>,
    var onClick : (Int) -> Unit
) : RecyclerView.Adapter<TeamAdapter.TeamHolder>(){

    class TeamHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View
        init {
            this.view = v
        }
        fun setName(name:String){
            var textViewName: TextView = view.findViewById(R.id.textViewName)
            textViewName.text = name
        }
        fun setStadium(name:String){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var textViewStadium: TextView = view.findViewById(R.id.textViewStadium)
            textViewStadium.text = name
        }
        fun setAvatar(url:String){ // tiene que ser generica para trabajar con el item ACTUAL de la lista
            var imageViewAvatar: ImageView = view.findViewById(R.id.imageViewAvatar)
            Glide.with(view).load(url).into(imageViewAvatar)
        }

        fun getCard(): CardView {
            return view.findViewById(R.id.cardPlayer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return (TeamHolder(view))
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        holder.setName(teamList[position].name)
        holder.setStadium(teamList[position].stadiumName)
        holder.setAvatar(teamList[position].urlAvatar)
        holder.getCard().setOnClickListener {
            onClick(position)
        }
    }

}