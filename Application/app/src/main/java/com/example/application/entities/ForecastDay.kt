package com.example.application.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastDay(
    @SerializedName("date") var date: String,
    @SerializedName("date_epoch") var dateEpoch: Number,
    @SerializedName("day") var day: Day,
    @SerializedName("astro") var astro: Astro,
    @SerializedName("hour") var hour: MutableList<Hour>
) : Parcelable
