package com.example.helloworld.entities

import android.os.Parcelable
import android.util.Log
import kotlinx.parcelize.Parcelize

@Parcelize
class User (
    var name: String,
    var lastName: String = "",
    var age: Int = 0,
    var email: String = "",
    private var password: String // Atributo privado
    ) : Parcelable { // kotlin tiene el contructor inline

    fun getPassword() : String{
        return this.password
    }
}