package com.example.application.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.application.R
import com.example.application.activities.LoginActivity
import com.example.application.activities.MainActivity
import com.example.application.activities.SettingsActivity
import com.example.application.entities.User
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.Locale

class UserDetailFragment : Fragment() {

    private val PREF_NAME = "myPreferences"

    private lateinit var v: View
    private lateinit var user: User

    private lateinit var btnSettings : Button
    private lateinit var btnLogOut : Button

    private lateinit var textViewUserName : TextView
    private lateinit var textUserEmail : TextView
    private lateinit var textViewUserTeam: TextView
    private lateinit var textViewUserCity : TextView
    private lateinit var imageViewUserAvatar : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_user_detail, container, false)

        val sharedPref: SharedPreferences = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        val str = sharedPref.getString("USER", "")
        val user = GsonBuilder().create().fromJson(str, User::class.java)

        textViewUserName = v.findViewById(R.id.textViewUserName)
        textUserEmail = v.findViewById(R.id.textUserEmail)
        textViewUserTeam = v.findViewById(R.id.textViewUserTeam)
        textViewUserCity = v.findViewById(R.id.textViewUserCity)
        imageViewUserAvatar = v.findViewById(R.id.imageViewUserAvatar)

        textViewUserName.text = user.username.capitalize()
        textUserEmail.text = user.email
        textViewUserTeam.text = user.club
        textViewUserCity.text = user.city

        Glide.with(v).load(user.img).into(imageViewUserAvatar)

        btnSettings = v.findViewById(R.id.btnSettings)
        btnLogOut = v.findViewById(R.id.btnLogOut)

        btnSettings.setOnClickListener {
            startActivity(Intent(context, SettingsActivity::class.java))
        }

        btnLogOut.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("¿Está seguro que desea salir?")
                .setCancelable(true)
                .setPositiveButton("Si"){ dialog, id ->

                    val sharedPref: SharedPreferences = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putString("USER", "").apply()

                    startActivity(Intent(activity, LoginActivity::class.java))
                    activity?.finish()
                }
                .setNegativeButton("No") { dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

        return v
    }
}