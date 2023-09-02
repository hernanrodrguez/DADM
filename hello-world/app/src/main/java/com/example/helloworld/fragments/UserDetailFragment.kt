package com.example.helloworld.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.helloworld.R
import com.example.helloworld.entities.User

class UserDetailFragment : Fragment() {

    lateinit var txtLabelWelcome : TextView
    lateinit var arg : User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_user_details, container, false)

        txtLabelWelcome = v.findViewById(R.id.txtLabelWelcome)

        return v
    }

    override fun onStart() {
        super.onStart()

        arg = UserDetailFragmentArgs.fromBundle(requireArguments()).user
        txtLabelWelcome.text = "Bienvenido ${arg.name}"
    }

}