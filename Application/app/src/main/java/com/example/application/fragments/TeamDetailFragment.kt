package com.example.application.fragments

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
import com.bumptech.glide.Glide
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
    private lateinit var textViewNationalCups : TextView
    private lateinit var textViewTeamLocation : TextView
    private lateinit var textViewTeamFoundation : TextView
    private lateinit var imageViewTeamAvatar: ImageView
    private lateinit var btnEditTeam : Button
    private lateinit var btnRemoveTeam : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_team_detail, container, false)

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

        btnEditTeam.setOnClickListener {
            val action = TeamDetailFragmentDirections.actionTeamDetailFragmentToTeamAddFragment(arg)
            findNavController().navigate(action)
        }

        return v
    }

    override fun onStart() {
        super.onStart()

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

}