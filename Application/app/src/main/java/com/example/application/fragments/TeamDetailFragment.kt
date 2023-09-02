package com.example.application.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.application.R
import com.example.application.entities.Team
import com.example.application.entities.User

class TeamDetailFragment : Fragment() {

    private lateinit var v : View
    private lateinit var arg : Team

    private lateinit var textViewTeamName : TextView
    private lateinit var textViewTeamStadium : TextView
    private lateinit var textViewNationalTitles : TextView
    private lateinit var textViewInternationalTitles : TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_team_detail, container, false)

        textViewTeamName = v.findViewById(R.id.textViewTeamName)
        textViewTeamStadium = v.findViewById(R.id.textViewTeamStadium)
        textViewNationalTitles = v.findViewById(R.id.textViewNationalTitles)
        textViewInternationalTitles = v.findViewById(R.id.textViewInternationalTitles)

        return v
    }

    override fun onStart() {
        super.onStart()

        arg = TeamDetailFragmentArgs.fromBundle(requireArguments()).team

        textViewTeamName.text = "Club: ${arg.name}"
        textViewTeamStadium.text = "Estadio: ${arg.name}"
        textViewNationalTitles.text = "Titulos Nacionales: ${arg.nationalTitles}"
        textViewInternationalTitles.text = "Titulos Internacionales: ${arg.internationalTitles}"
    }

}