package com.example.application.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.preference.PreferenceManager
import com.example.application.R
import com.example.application.database.AppDatabase
import com.example.application.database.TeamDao
import com.example.application.entities.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.Locale

class LoginActivity : AppCompatActivity() {

    private val TAG = "LOGIN"

    private lateinit var auth: FirebaseAuth

    private lateinit var btnLogin : Button
    private lateinit var btnSignUp : Button
    private lateinit var editTextUsername : EditText
    private lateinit var editTextPassword : EditText
    private lateinit var snackbar : Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        btnLogin = findViewById(R.id.btnLogin)
        btnSignUp = findViewById(R.id.btnSignUp)
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)

    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI(currentUser)
        }

        btnLogin.setOnClickListener {

            if(editTextUsername.text.isEmpty()) {
                showSnackbar("Ingrese su correo electrónico")
            } else if(editTextPassword.text.isEmpty()) {
                showSnackbar("Ingrese su constraseña")
            } else if(editTextPassword.text.length < 6) {
                showSnackbar("Contraseña invalida")
            } else if(editTextUsername.text.isNotEmpty() && editTextPassword.text.isNotEmpty()) {
                signIn(editTextUsername.text.toString(), editTextPassword.text.toString())
            }
        }

        btnSignUp.setOnClickListener {
            if(editTextUsername.text.isEmpty()) {
                showSnackbar("Ingrese su correo electrónico")
            } else if(editTextPassword.text.isEmpty()) {
                showSnackbar("Ingrese su constraseña")
            } else if(editTextPassword.text.length < 6) {
                showSnackbar("Contraseña invalida")
            } else if(editTextUsername.text.isNotEmpty() && editTextPassword.text.isNotEmpty()) {
                createAccount(editTextUsername.text.toString(), editTextPassword.text.toString())
            }
        }
    }

    private fun showSnackbar(msg : String){
        snackbar = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    showSnackbar("La autenticación falló")
                    updateUI(null)
                }
            }
        // [END create_user_with_email]
    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    showSnackbar("La autenticación falló")
                    updateUI(null)
                }
            }
        // [END sign_in_with_email]
    }

    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                showSnackbar("Verifique su casilla de correo")
            }
        // [END send_email_verification]
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            if(user.isEmailVerified) {
                val mIntent = Intent(this, MainActivity::class.java)
                val mBundle = Bundle()
                mBundle.putString("user", user.uid)
                mIntent.putExtras(mBundle)
                Log.d(TAG, "Ya puse el extra")
                startActivity(mIntent)
                finish()
            } else {
                sendEmailVerification()
                showSnackbar("Valide su correo y vuelva a ingresar")
            }
        } else {

        }
    }

    private fun reload() {
    }


}