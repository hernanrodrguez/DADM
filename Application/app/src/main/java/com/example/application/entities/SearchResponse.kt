package com.example.application.entities

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("") var searchCities: SearchCity
)
