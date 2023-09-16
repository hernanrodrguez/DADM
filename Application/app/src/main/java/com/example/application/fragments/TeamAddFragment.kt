package com.example.application.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import com.example.application.R
import com.example.application.adapters.TeamAdapter
import com.example.application.database.AppDatabase
import com.example.application.database.TeamDao
import com.example.application.entities.Team
import com.example.application.entities.User
import com.google.android.material.snackbar.Snackbar

class TeamAddFragment : Fragment() {

    private lateinit var v : View

    private lateinit var snackbar : Snackbar

    private var db: AppDatabase? = null
    private var teamDao: TeamDao? = null

    private lateinit var newTeam : Team

    private lateinit var etTeamName : EditText
    private lateinit var etNationalTitles : EditText
    private lateinit var etInternationalTitles : EditText
    private lateinit var etNationalCups : EditText
    private lateinit var etLocation : EditText
    private lateinit var etStadium : EditText
    private lateinit var etStadiumCapacity : EditText
    private lateinit var etYear : EditText
    private lateinit var etURL : EditText
    private lateinit var btnAdd : Button

    private companion object{
        const val ERROR = -1
        const val NO_TEAM_NAME = 0
        const val NO_NATIONAL_TITLES = 1
        const val NO_INTERNATIONAL_TITLES = 2
        const val NO_NATIONAL_CUPS = 3
        const val NO_LOCATION = 4
        const val NO_STADIUM = 5
        const val NO_STADIUM_CAPACITY = 6
        const val NO_YEAR = 7
        const val NO_URL = 8
        const val INVALID_URL = 9
        const val OK = 10
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_team_add, container, false)

        etTeamName = v.findViewById(R.id.etTeamName)
        etNationalTitles = v.findViewById(R.id.etNationalTitles)
        etInternationalTitles = v.findViewById(R.id.etInternationalTitles)
        etNationalCups = v.findViewById(R.id.etNationalCups)
        etLocation = v.findViewById(R.id.etLocation)
        etStadium = v.findViewById(R.id.etStadium)
        etStadiumCapacity = v.findViewById(R.id.etStadiumCapacity)
        etYear = v.findViewById(R.id.etYear)
        etURL = v.findViewById(R.id.etURL)
        btnAdd = v.findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {

            when(checkFields()){
                NO_TEAM_NAME -> showSnackbar("Ingrese nombre del equipo")
                NO_NATIONAL_TITLES -> showSnackbar("Ingrese titulos locales")
                NO_INTERNATIONAL_TITLES -> showSnackbar("Ingrese titulos internacionales")
                NO_NATIONAL_CUPS -> showSnackbar("Ingrese copas nacionales")
                NO_LOCATION -> showSnackbar("Ingrese ubicacion del equipo")
                NO_STADIUM -> showSnackbar("Ingrese estadio")
                NO_STADIUM_CAPACITY -> showSnackbar("Ingrese capacidad del estadio")
                NO_YEAR -> showSnackbar("Ingrese año de fundacion")
                NO_URL -> showSnackbar("Ingrese URL al escudo")
                OK -> {
                    newTeam = Team(
                        0,
                        etTeamName.text.toString(),
                        etNationalTitles.text.toString().toInt(),
                        etInternationalTitles.text.toString().toInt(),
                        etYear.text.toString().toInt(),
                        etStadium.text.toString(),
                        etStadiumCapacity.text.toString().toInt(),
                        etURL.text.toString(),
                        etNationalCups.text.toString().toInt(),
                        etLocation.text.toString()
                    )
                }
            }
            if(this::newTeam.isInitialized) {
                teamDao?.insertTeam(newTeam)
                showSnackbar("Nuevo equipo agregado!")
            }
        }

        return v
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(v.context)
        teamDao = db?.teamDao()

    }

    private fun checkFields() : Int {
        if(etTeamName.text.isEmpty()){
            return NO_TEAM_NAME
        }
        if(etNationalTitles.text.isEmpty()){
            return NO_NATIONAL_TITLES
        }
        if(etInternationalTitles.text.isEmpty()){
            return NO_INTERNATIONAL_TITLES
        }
        if(etNationalCups.text.isEmpty()){
            return NO_NATIONAL_CUPS
        }
        if(etLocation.text.isEmpty()){
            return NO_LOCATION
        }
        if(etStadium.text.isEmpty()){
            return NO_STADIUM
        }
        if(etStadiumCapacity.text.isEmpty()){
            return NO_STADIUM_CAPACITY
        }
        if(etYear.text.isEmpty()){
            return NO_YEAR
        }
        if(etURL.text.isEmpty()){
            return NO_URL
        }
        return OK
    }

    private fun showSnackbar(msg : String){
        snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

}