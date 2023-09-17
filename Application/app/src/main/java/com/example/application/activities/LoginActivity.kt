package com.example.application.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.application.R
import com.example.application.database.AppDatabase
import com.example.application.database.TeamDao
import com.example.application.entities.User
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogin : Button
    private lateinit var editTextUsername : EditText
    private lateinit var editTextPassword : EditText
    private lateinit var snackbar : Snackbar
    private lateinit var currentUser : User

    private var db: AppDatabase? = null
    private var userDao: TeamDao? = null

    private companion object{
        const val ERROR = -1
        const val NO_USERNAME = 0
        const val NO_PASSWORD = 1
        const val NO_REGISTER = 2
        const val WRONG_PASSWORD = 3
        const val LOGIN_OK = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnLogin)
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(this)
        userDao = db?.teamDao()

        userDao?.fetchAllUsers()

        val usersList : MutableList<User> = userDao?.fetchAllUsers().orEmpty().filterNotNull().toMutableList()

        btnLogin.setOnClickListener {

            when (checkCredentials(
                usersList,
                editTextUsername.text.toString(),
                editTextPassword.text.toString()
            )) {
                NO_USERNAME -> showSnackbar("Enter username")

                NO_PASSWORD -> showSnackbar("Enter password")

                NO_REGISTER -> showSnackbar("Username not registered")

                WRONG_PASSWORD -> showSnackbar("Wrong password")

                LOGIN_OK -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

                ERROR -> showSnackbar("There was a problem!")
            }
        }
    }

    private fun showSnackbar(msg : String){
        snackbar = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG)
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