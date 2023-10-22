package com.example.application.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Astro(
    @SerializedName("sunrise") var sunrise: String,
    @SerializedName("sunset") var sunset: String,
    @SerializedName("moonrise") var moonrise: String,
    @SerializedName("moonset") var moonset: String,
    @SerializedName("moon_phase") var moonPhase: String,
    @SerializedName("moon_illumination") var moonIllumination: String,
    @SerializedName("is_moon_up") var isMoonUp: Number,
    @SerializedName("is_sun_up") var isSunUp: Number

) : Parcelable
