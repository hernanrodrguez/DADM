package com.example.application.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastDays(
    @SerializedName("forecastday") var forecastDays: MutableList<ForecastDay>
) : Parcelable
