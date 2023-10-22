package com.example.application.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastResponse(
    @SerializedName("location") var location: Location,
    @SerializedName("current") var current: Current,
    @SerializedName("forecast") var forecast: ForecastDays
) : Parcelable

