package com.example.application.fragments

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.application.R
import com.example.application.activities.LoginActivity
import com.example.application.activities.MainActivity
import com.example.application.activities.SettingsActivity

class UserDetailFragment : Fragment() {
    private lateinit var v: View

    private lateinit var btnSettings : Button
    private lateinit var btnLogOut : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_user_detail, container, false)

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