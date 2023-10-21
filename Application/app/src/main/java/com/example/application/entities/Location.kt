package com.example.application.entities

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name") var name: String,
    @SerializedName("region") var region: String,
    @SerializedName("country") var country: String,
    @SerializedName("lat") var lat: Float,
    @SerializedName("lon") var lon: Float,
    @SerializedName("tz_id") var tzId: String,
    @SerializedName("localtime_epoch") var localtimeEpoch: Int,
    @SerializedName("localtime") var localtime: String
)
