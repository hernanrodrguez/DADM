package com.example.application.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hour(
    @SerializedName("time_epoch") var timeEpoch: Number,
    @SerializedName("time") var time: String,
    @SerializedName("temp_c") var tempC: Number,
    @SerializedName("temp_f") var tempF: Number,
    @SerializedName("is_day") var isDay: Number,
    @SerializedName("condition") var condition: Condition,
    @SerializedName("wind_mph") var windMph: Number,
    @SerializedName("wind_kph") var windKph: Number,
    @SerializedName("wind_degree") var windDegree: Number,
    @SerializedName("wind_dir") var windDir: Number,
    @SerializedName("pressure_mb") var pressureMb: Number,
    @SerializedName("pressure_in") var pressureIn: Number,
    @SerializedName("precip_mm") var precipMm: Number,
    @SerializedName("precip_in") var precipIn: Number,
    @SerializedName("humidity") var humidity: Number,
    @SerializedName("cloud") var cloud: Number,
    @SerializedName("feelslike_c") var feelsLikeC: Number,
    @SerializedName("feelslike_f") var feelsLikeF: Number,
    @SerializedName("windchill_c") var windChillC: Number,
    @SerializedName("windchill_f") var windChillF: Number,
    @SerializedName("heatindex_c") var heatIndexC: Number,
    @SerializedName("heatindex_f") var heatIndexF: Number,
    @SerializedName("dewpoint_c") var dewPointC: Number,
    @SerializedName("dewpoint_f") var dewPointF: Number,
    @SerializedName("will_it_rain") var willItRain: Number,
    @SerializedName("chance_of_rain") var chanceOfRain: Number,
    @SerializedName("will_it_snow") var willItSnow: Number,
    @SerializedName("chance_of_snow") var chanceOfSnow: Number,
    @SerializedName("vis_km") var visKm: Number,
    @SerializedName("vis_miles") var visMiles: Number,
    @SerializedName("gust_mph") var gustMph: Number,
    @SerializedName("gust_kph") var gustKph: Number,
    @SerializedName("uv") var uv: Number
) : Parcelable