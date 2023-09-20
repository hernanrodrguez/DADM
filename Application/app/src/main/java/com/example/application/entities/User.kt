package com.example.application.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "club")
    var club: String,

    @ColumnInfo(name = "city")
    var city: String,

    @ColumnInfo(name = "img")
    var img: String,

    @ColumnInfo(name = "password")
    private var password: String
) : Parcelable {

    fun getPassword() : String{
        return this.password
    }
}