package com.example.application.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.application.R
import com.example.application.adapters.TeamAdapter
import com.example.application.database.AppDatabase
import com.example.application.database.TeamDao
import com.example.application.entities.Team
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class TeamsDashboardFragment : Fragment() {

    private lateinit var v : View
    private lateinit var recTeams : RecyclerView
    private lateinit var adapter : TeamAdapter
    private lateinit var snackbar : Snackbar
    lateinit var fab: FloatingActionButton

    private var db: AppDatabase? = null
    private var teamDao: TeamDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_teams_dashboard, container, false)

        recTeams = v.findViewById(R.id.recTeams)
        fab = v.findViewById(R.id.fab)

        fab.setOnClickListener {
            val action = TeamsDashboardFragmentDirections.actionTeamsDashboardFragmentToTeamAddFragment()
            findNavController().navigate(action)
        }

        return v
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(v.context)
        teamDao = db?.teamDao()

        teamDao?.fetchAllTeams()

        val teamList : MutableList<Team> = teamDao?.fetchAllTeams().orEmpty().filterNotNull().toMutableList()

        adapter = TeamAdapter(
            teamList,
            {
                val action =
                    TeamsDashboardFragmentDirections.actionTeamsDashboardFragmentToTeamDetailFragment(
                        teamList[it]
                    )
                findNavController().navigate(action)
            },
            {
                val builder = AlertDialog.Builder(context)
                builder.setMessage(teamList[it].name)
                    .setCancelable(true)
                    .setPositiveButton("Editar"){ dialog, id ->
                        val action =
                            TeamsDashboardFragmentDirections.actionTeamsDashboardFragmentToTeamAddFragment(
                                teamList[it]
                            )
                        findNavController().navigate(action)
                    }
                    .setNegativeButton("Eliminar") { dialog, id ->
                        val del_builder = AlertDialog.Builder(context)
                        del_builder.setMessage("¿Está seguro que desea eliminar este equipo?")
                            .setCancelable(true)
                            .setPositiveButton("Si"){ dialog, id ->
                                teamDao?.delete(teamList[it])
                                showSnackbar("Equipo eliminado exitosamente!")
                                teamList.removeAt(it)
                                recTeams.adapter?.notifyItemRemoved(it)

                            }
                            .setNegativeButton("No") { dialog, id ->
                                dialog.dismiss()
                            }
                        val alert = del_builder.create()
                        alert.show()
                    }
                val alert = builder.create()
                alert.show()
            }
        )
        recTeams.layoutManager = LinearLayoutManager(context)
        recTeams.adapter = adapter

    }

    private fun showSnackbar(msg : String){
        snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

}