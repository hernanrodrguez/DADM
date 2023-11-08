package com.example.application.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchCity(
    @SerializedName("id") var id: Number,
    @SerializedName("name") var name: String,
    @SerializedName("region") var region: String,
    @SerializedName("country") var country: String,
    @SerializedName("lat") var lat: String,
    @SerializedName("lon") var lon: String,
    @SerializedName("url") var url: String
) : Parcelable
