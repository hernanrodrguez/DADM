package com.example.helloworld.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.NavArgs
import com.example.helloworld.R
import com.example.helloworld.entities.User

class SecondFragment : Fragment() {

    lateinit var txtLabelWelcome : TextView
    lateinit var arg : User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_second, container, false)

        txtLabelWelcome = v.findViewById(R.id.txtLabelWelcome)

        return v
    }

    override fun onStart() {
        super.onStart()

        arg = SecondFragmentArgs.fromBundle(requireArguments()).user
        txtLabelWelcome.text = "Bienvenido ${arg.name}"
    }

}