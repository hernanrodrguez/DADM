package com.example.application.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.application.R
import com.example.application.activities.LoginActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LogoutFragment : Fragment() {

    companion object {
        fun newInstance() = LogoutFragment()
    }

    private lateinit var viewModel: LogoutViewModel

    private lateinit var v: View
    private lateinit var btnLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_logout, container, false)

        btnLogout = v.findViewById(R.id.btnLogout)

        btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LogoutViewModel::class.java)
        // TODO: Use the ViewModel
    }

}