package com.example.application.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Day(
    @SerializedName("maxtemp_c") var maxTempC: Number,
    @SerializedName("maxtemp_f") var maxTempF: Number,
    @SerializedName("mintemp_c") var minTempC: Number,
    @SerializedName("mintemp_f") var minTempF: Number,
    @SerializedName("avgtemp_c") var avgTempC: Number,
    @SerializedName("avgtemp_f") var avgTempF: Number,
    @SerializedName("maxwind_mph") var maxWindMph: Number,
    @SerializedName("maxwind_kph") var maxWindKph: Number,
    @SerializedName("totalprecip_mm") var totalPrecipMm: Number,
    @SerializedName("totalprecip_in") var totalPrecipIn: Number,
    @SerializedName("totalsnow_cm") var totalSnowCm: Number,
    @SerializedName("avgvis_km") var avgVisKm: Number,
    @SerializedName("avgvis_miles") var avgVisMiles: Number,
    @SerializedName("avghumidity") var avgHumidity: Number,
    @SerializedName("daily_will_it_rain") var dailyWillItRain: Number,
    @SerializedName("daily_chance_of_rain") var dailyChanceOfRain: Number,
    @SerializedName("daily_will_it_snow") var dailyWillItSnow: Number,
    @SerializedName("daily_chance_of_snow") var dailyChanceOfSnow: Number,
    @SerializedName("condition") var condition: Condition,
    @SerializedName("uv") var uv: Number
) : Parcelable
