package com.example.application.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.preference.PreferenceManager
import com.example.application.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 2000
    private val PREF_NAME = "myPreferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(

            {
                val sharedPref: SharedPreferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                val str = sharedPref.getString("USER", "")
                if(str == "") {
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                finish()
            }
            , SPLASH_TIME_OUT)
    }

    override fun onStart() {
        super.onStart()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if(prefs.getBoolean("theme", true)){
            setTheme(R.style.Base_Theme_Application)
        } else {
            setTheme(R.style.Theme_Application)
        }

    }
}