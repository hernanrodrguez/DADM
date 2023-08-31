package com.example.helloworld.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.helloworld.R
import com.example.helloworld.entities.User
import com.google.android.material.snackbar.Snackbar

class FirstFragment : Fragment() {

    lateinit var v : View
    lateinit var btnNavigate : Button
    lateinit var editTextUsername : EditText
    lateinit var editTextPassword : EditText
    lateinit var snackbar: Snackbar

    var user1 : User = User(name = "juan", password = "pass")
    var user2 : User = User(name = "pedro", password = "1234")
    var user3 : User = User(name = "simon", password = "clave")
    var user4 : User = User(name = "jose", password = "jjjj")

    var userList : MutableList<User> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_first, container, false)
        btnNavigate = v.findViewById(R.id.btnNavigate)
        editTextUsername = v.findViewById(R.id.editTextUsername)
        editTextPassword = v.findViewById(R.id.editTextPassword)

        return v
    }

    override fun onStart() {
        super.onStart()

        userList.add(user1)
        userList.add(user2)
        userList.add(user3)
        userList.add(user4)

        btnNavigate.setOnClickListener{ it ->

            if(editTextUsername.text.isEmpty()){
                snackbar = Snackbar.make(it,"Ingrese username",Snackbar.LENGTH_LONG)
                snackbar.show()
            } else if(editTextPassword.text.isEmpty()){
                snackbar = Snackbar.make(it,"Ingrese password",Snackbar.LENGTH_LONG)
                snackbar.show()
            } else if(!userList.any { user -> user.name == editTextUsername.text.toString()}) {
                snackbar = Snackbar.make(it,"Usuario no registrado",Snackbar.LENGTH_LONG)
                snackbar.show()
            } else {
                val currentUser = userList.firstOrNull{it.name == editTextUsername.text.toString()}
                if (currentUser != null) {
                    if (currentUser.getPassword() == editTextPassword.text.toString()){
                        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentUser)
                        findNavController().navigate(action)
                    } else {
                        snackbar = Snackbar.make(it,"Clave incorrecta",Snackbar.LENGTH_LONG)
                        snackbar.show()
                    }
                }
            }
        }
    }

}