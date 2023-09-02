package com.example.application.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User (
    var username: String,
    private var password: String
) : Parcelable {

    fun getPassword() : String{
        return this.password
    }
}