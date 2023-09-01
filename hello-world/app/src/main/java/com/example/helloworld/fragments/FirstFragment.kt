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

    private lateinit var v : View
    private lateinit var btnNavigate : Button
    private lateinit var editTextUsername : EditText
    private lateinit var editTextPassword : EditText
    private lateinit var snackbar : Snackbar

    private lateinit var currentUser : User

    private var user1 : User = User(name = "juan", password = "pass")
    private var user2 : User = User(name = "pedro", password = "1234")
    private var user3 : User = User(name = "simon", password = "clave")
    private var user4 : User = User(name = "jose", password = "jjjj")

    private var userList : MutableList<User> = mutableListOf()

    private companion object{
        const val ERROR = -1
        const val NO_USERNAME = 0
        const val NO_PASSWORD = 1
        const val NO_REGISTER = 2
        const val WRONG_PASSWORD = 3
        const val LOGIN_OK = 4
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_first, container, false)

        btnNavigate = v.findViewById(R.id.btnNavigate)
        editTextUsername = v.findViewById(R.id.editTextUsername)
        editTextPassword = v.findViewById(R.id.editTextPassword)

        userList.add(user1)
        userList.add(user2)
        userList.add(user3)
        userList.add(user4)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnNavigate.setOnClickListener{

            when(checkCredentials(userList, editTextUsername.text.toString(), editTextPassword.text.toString())){
                NO_USERNAME -> {
                    showSnackbar("Ingrese username")
                }
                NO_PASSWORD -> {
                    showSnackbar("Ingrese password")
                }
                NO_REGISTER -> {
                    showSnackbar("Usuario no registrado")
                }
                WRONG_PASSWORD -> {
                    showSnackbar("Clave incorrecta")
                }
                LOGIN_OK -> {
                    val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentUser)
                    findNavController().navigate(action)
                }
                ERROR -> {
                    showSnackbar("There was a problem!")
                }
            }
        }
    }

    private fun showSnackbar(msg : String){
        snackbar = Snackbar.make(v, msg,Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    private fun checkCredentials(list : MutableList<User>, username : String, password : String) : Int {
        if(username.isEmpty()){
            return NO_USERNAME
        }
        if(password.isEmpty()){
            return NO_PASSWORD
        }
        if(!list.any { user -> user.name == username }){
            return NO_REGISTER
        } else {
            val userExists = list.firstOrNull{ it.name == username }
            if(userExists != null){
                currentUser = userExists
                return if (currentUser.getPassword() == password){
                    LOGIN_OK
                } else {
                    WRONG_PASSWORD
                }
            }
        }
        return ERROR
    }
}