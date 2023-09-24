package com.example.application.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.preference.PreferenceManager
import com.example.application.R
import com.example.application.database.AppDatabase
import com.example.application.database.TeamDao
import com.example.application.entities.User
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.Locale

class LoginActivity : AppCompatActivity() {

    private val PREF_NAME = "myPreferences"

    private lateinit var btnLogin : Button
    private lateinit var editTextUsername : EditText
    private lateinit var editTextPassword : EditText
    private lateinit var snackbar : Snackbar
    private lateinit var currentUser : User

    private var db: AppDatabase? = null
    private var userDao: TeamDao? = null

    private companion object{
        const val ERROR = -1
        const val NO_USERNAME = 0
        const val NO_PASSWORD = 1
        const val NO_REGISTER = 2
        const val WRONG_PASSWORD = 3
        const val LOGIN_OK = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if(prefs.getString("lang", "es") == "en"){
            val locale = Locale("en")
            val config = Configuration(resources.configuration)
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)
        } else {
            val systemLocale = Resources.getSystem().configuration.locale
            // Configura la configuración regional de la aplicación a la configuración regional del sistema
            val config = Configuration(resources.configuration)
            config.setLocale(systemLocale)
            resources.updateConfiguration(config, resources.displayMetrics)
        }

        btnLogin = findViewById(R.id.btnLogin)
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)

        btnLogin.text = getString(R.string.login)
        editTextUsername.hint = getString(R.string.username)
        editTextPassword.hint = getString(R.string.password)
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(this)
        userDao = db?.teamDao()

        userDao?.fetchAllUsers()

        val usersList : MutableList<User> = userDao?.fetchAllUsers().orEmpty().filterNotNull().toMutableList()

        btnLogin.setOnClickListener {

            when (checkCredentials(
                usersList,
                editTextUsername.text.toString(),
                editTextPassword.text.toString()
            )) {
                NO_USERNAME -> showSnackbar("Enter username")

                NO_PASSWORD -> showSnackbar("Enter password")

                NO_REGISTER -> showSnackbar("Username not registered")

                WRONG_PASSWORD -> showSnackbar("Wrong password")

                LOGIN_OK -> {
                    val sharedPref: SharedPreferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()

                    val json: String = GsonBuilder().create().toJson(currentUser)
                    editor.putString("USER", json).apply()

                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

                ERROR -> showSnackbar("There was a problem!")
            }
        }
    }

    private fun showSnackbar(msg : String){
        snackbar = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    private fun checkCredentials(list : MutableList<User>, username : String, password : String) : Int {
        if(username.isEmpty()){
            return NO_USERNAME
        }
        if(password.isEmpty()){
            return NO_PASSWORD
        }
        if(!list.any { user -> user.username == username }){
            return NO_REGISTER
        } else {
            val userExists = list.firstOrNull{ it.username == username }
            if(userExists != null){
                currentUser = userExists
                return if (currentUser.getPassword() == password){
                    LOGIN_OK
                } else {
                    WRONG_PASSWORD
                }
            }
        }
        return ERROR
    }
}