package com.example.application.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Team (
    var name: String,
    var nationalTitles: Int,
    var internationalTitles: Int,
    var foundationYear: Int,
    var stadiumName: String,
    var stadiumCapacity: Int,
    var urlAvatar: String
) : Parcelable {
}