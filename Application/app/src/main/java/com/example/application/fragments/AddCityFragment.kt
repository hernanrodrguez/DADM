package com.example.application.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.application.R
import com.example.application.activities.MainActivity
import com.example.application.adapters.CityAdapter
import com.example.application.adapters.CitySearchAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class AddCityFragment : Fragment() {

    private val PREF_NAME = "myPreferences"

    companion object {
        fun newInstance() = AddCityFragment()
    }

    lateinit var v: View
    private lateinit var userid: String
    private lateinit var recNewCities: RecyclerView
    private lateinit var adapter: CitySearchAdapter
    private lateinit var etFilterCities : EditText

    private lateinit var viewModel: AddCityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_add_city, container, false)
        userid = (activity as MainActivity).getUser()

        recNewCities = v.findViewById(R.id.recNewCities)
        etFilterCities = v.findViewById(R.id.etFilterCities)

        etFilterCities.setOnEditorActionListener{ v, actionId, event ->
            if(actionId ==  EditorInfo.IME_ACTION_SEARCH){
                lifecycleScope.launch {
                    val citiesList = viewModel.searchCities(etFilterCities.text.toString())
                    adapter = CitySearchAdapter(
                        citiesList
                    )
                    {
                        val builder = AlertDialog.Builder(context)
                        builder.setMessage(citiesList[it].name)
                            .setCancelable(true)
                            .setPositiveButton("Agregar ciudad") { dialog, id ->
                                val sharedPref: SharedPreferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                                var json = sharedPref.getString(userid, "")

                                val list : MutableList<String> = if (json.isNullOrEmpty()) {
                                    mutableListOf()
                                } else {
                                    Gson().fromJson(json, object : TypeToken<MutableList<String>>() {}.type)
                                }

                                list.add(citiesList[it].name)

                                json = Gson().toJson(list)
                                sharedPref.edit().putString(userid, json).apply()

                                val action = AddCityFragmentDirections.actionAddCityFragmentToCityCurrentDetailFragment(citiesList[it])
                                findNavController().navigate(action)
                            }
                            .setNegativeButton("Salir") { dialog, id ->
                                dialog.dismiss()
                            }
                        val alert = builder.create()
                        alert.show()
                        Log.d("ADD CITY", citiesList[0].name)
                    }
                    recNewCities.layoutManager = LinearLayoutManager(context)
                    recNewCities.adapter = adapter
                }
                true
            } else {
                false
            }
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddCityViewModel::class.java)
        // TODO: Use the ViewModel
    }

}