package com.example.application.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.application.R
import com.example.application.entities.Team
import com.example.application.entities.User
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private lateinit var v : View
    private lateinit var btnLogin : Button
    private lateinit var editTextUsername : EditText
    private lateinit var editTextPassword : EditText
    private lateinit var snackbar : Snackbar
    private lateinit var currentUser : User

    private var user1 : User = User("juan", "pass")
    private var user2 : User = User("pedro", "1234")

    private var usersList : MutableList<User> = mutableListOf()

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
        v = inflater.inflate(R.layout.fragment_login, container, false)

        btnLogin = v.findViewById(R.id.btnLogin)
        editTextUsername = v.findViewById(R.id.editTextUsername)
        editTextPassword = v.findViewById(R.id.editTextPassword)

        usersList.add(user1)
        usersList.add(user2)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnLogin.setOnClickListener{

        when(checkCredentials(usersList, editTextUsername.text.toString(), editTextPassword.text.toString())){
            NO_USERNAME -> {
                showSnackbar("Enter username")
            }
            NO_PASSWORD -> {
                showSnackbar("Enter password")
            }
            NO_REGISTER -> {
                showSnackbar("Username not registered")
            }
            WRONG_PASSWORD -> {
                showSnackbar("Wrong password")
            }
            LOGIN_OK -> {
                val action = LoginFragmentDirections.actionLoginFragmentToTeamsDashboardFragment(currentUser)
                findNavController().navigate(action)
            }
            ERROR -> {
                showSnackbar("There was a problem!")
            }
        }
    }
}


    private fun showSnackbar(msg : String){
        snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    private fun checkCredentials(list : MutableList<User>, username : String, password : String) : Int {
        if(username.isEmpty()){
            return NO_USERNAME
        }
        if(password.isEmpty()){
            return NO_PASSWORD
        }
        if(!list.any { user -> user.username == username }){
            return NO_REGISTER
        } else {
            val userExists = list.firstOrNull{ it.username == username }
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