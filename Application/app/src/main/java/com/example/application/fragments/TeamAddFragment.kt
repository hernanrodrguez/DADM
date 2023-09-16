package com.example.application.fragments

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
    private  var arg : Team? = null

    private lateinit var tvTitle : TextView
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

        tvTitle = v.findViewById(R.id.tvTitle)
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
                    if(arg != null){

                        val builder = AlertDialog.Builder(context)
                        builder.setMessage("¿Está seguro que desea modificar este equipo?")
                            .setCancelable(false)
                            .setPositiveButton("Si"){ dialog, id ->
                                arg!!.name = etTeamName.text.toString()
                                arg!!.nationalTitles = etNationalTitles.text.toString().toInt()
                                arg!!.internationalTitles = etInternationalTitles.text.toString().toInt()
                                arg!!.nationalCups = etNationalCups.text.toString().toInt()
                                arg!!.foundationYear = etYear.text.toString().toInt()
                                arg!!.stadiumName = etStadium.text.toString()
                                arg!!.stadiumCapacity = etStadiumCapacity.text.toString().toInt()
                                arg!!.urlAvatar = etURL.text.toString()
                                arg!!.location = etLocation.text.toString()

                                teamDao?.updateTeam(arg!!)
                                showSnackbar("Equipo modificado exitosamente!")
                                parentFragmentManager.popBackStack()
                            }
                            .setNegativeButton("No") { dialog, id ->
                                dialog.dismiss()
                            }
                        val alert = builder.create()
                        alert.show()

                    } else {

                        val builder = AlertDialog.Builder(context)
                        builder.setMessage("¿Está seguro que desea agregar este equipo?")
                            .setCancelable(false)
                            .setPositiveButton("Si"){ dialog, id ->
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
                                teamDao?.insertTeam(newTeam)
                                showSnackbar("Nuevo equipo agregado!")
                                parentFragmentManager.popBackStack()
                            }
                            .setNegativeButton("No") { dialog, id ->
                                dialog.dismiss()
                            }
                        val alert = builder.create()
                        alert.show()

                    }
                }
            }
        }
        return v
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(v.context)
        teamDao = db?.teamDao()
        arg = TeamAddFragmentArgs.fromBundle(requireArguments()).team

        if(arg != null){
            etTeamName.setText(arg!!.name)
            etNationalTitles.setText(arg!!.nationalTitles.toString())
            etInternationalTitles.setText(arg!!.internationalTitles.toString())
            etNationalCups.setText(arg!!.nationalCups.toString())
            etLocation.setText(arg!!.location)
            etStadium.setText(arg!!.stadiumName)
            etStadiumCapacity.setText(arg!!.stadiumCapacity.toString())
            etYear.setText(arg!!.foundationYear.toString())
            etURL.setText(arg!!.urlAvatar)

            tvTitle.text = "Editar Equipo"
            btnAdd.text = "Editar"
        }

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