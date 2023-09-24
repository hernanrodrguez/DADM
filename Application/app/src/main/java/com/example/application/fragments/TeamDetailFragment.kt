package com.example.application.fragments

import android.app.AlertDialog
import android.content.res.Configuration
import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import com.example.application.R
import com.example.application.database.AppDatabase
import com.example.application.database.TeamDao
import com.example.application.entities.Team
import com.example.application.entities.User
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

class TeamDetailFragment : Fragment() {

    private lateinit var v : View
    private lateinit var arg : Team

    private lateinit var snackbar : Snackbar

    private lateinit var textViewTeamName : TextView
    private lateinit var textViewTeamStadium : TextView
    private lateinit var textViewNationalTitles : TextView
    private lateinit var textViewInternationalTitles : TextView
    private lateinit var textViewNationalCups : TextView
    private lateinit var textViewTeamLocation : TextView
    private lateinit var textViewTeamFoundation : TextView
    private lateinit var imageViewTeamAvatar: ImageView
    private lateinit var btnEditTeam : Button
    private lateinit var btnRemoveTeam : Button

    private lateinit var tvNationalTitles : TextView
    private lateinit var tvInternationalTitles : TextView

    private var db: AppDatabase? = null
    private var teamDao: TeamDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_team_detail, container, false)

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
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

        textViewTeamName = v.findViewById(R.id.textViewTeamName)
        textViewTeamStadium = v.findViewById(R.id.textViewTeamStadium)
        textViewNationalTitles = v.findViewById(R.id.textViewNationalTitles)
        textViewInternationalTitles = v.findViewById(R.id.textViewInternationalTitles)
        textViewNationalCups = v.findViewById(R.id.textViewNationalCups)
        textViewTeamLocation = v.findViewById(R.id.textViewTeamLocation)
        textViewTeamFoundation = v.findViewById(R.id.textViewTeamFoundation)
        imageViewTeamAvatar = v.findViewById(R.id.imageViewTeamAvatar)
        btnEditTeam = v.findViewById(R.id.btnEditTeam)
        btnRemoveTeam = v.findViewById(R.id.btnRemoveTeam)

        tvNationalTitles = v.findViewById(R.id.tvNationalTitles)
        tvInternationalTitles = v.findViewById(R.id.tvInternationalTitles)

        tvNationalTitles.text = getString(R.string.torneos_n_locales)
        tvInternationalTitles.text = getString(R.string.copas_n_internacionales)

        btnEditTeam.setOnClickListener {
            val action = TeamDetailFragmentDirections.actionTeamDetailFragmentToTeamAddFragment(arg)
            findNavController().navigate(action)
        }

        btnRemoveTeam.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage(getString(R.string.confirmar_eliminar_equipo))
                .setCancelable(true)
                .setPositiveButton(getString(R.string.si)){ dialog, id ->
                    teamDao?.delete(arg)
                    showSnackbar(getString(R.string.equipo_eliminado_exitosamente))
                    parentFragmentManager.popBackStack()
                }
                .setNegativeButton(getString(R.string.no)) { dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

        return v
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(v.context)
        teamDao = db?.teamDao()
        arg = TeamDetailFragmentArgs.fromBundle(requireArguments()).team

        textViewTeamName.text = arg.name
        textViewTeamStadium.text = arg.stadiumName
        textViewNationalTitles.text = arg.nationalTitles.toString()
        textViewInternationalTitles.text = arg.internationalTitles.toString()
        textViewNationalCups.text = arg.nationalCups.toString()
        textViewTeamLocation.text = arg.location
        textViewTeamFoundation.text = arg.foundationYear.toString()
        Glide.with(v).load(arg.urlAvatar).into(imageViewTeamAvatar)

    }

    private fun showSnackbar(msg : String){
        snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

}