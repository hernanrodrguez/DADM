package com.example.application.entities

import com.google.gson.annotations.SerializedName

data class CurrentResponse(
    @SerializedName("location") var location: Location,
    @SerializedName("current") var current: Current
)
