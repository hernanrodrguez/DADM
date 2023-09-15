package com.example.application.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Parcelize
@Entity(tableName = "teams")
class Team (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "national_titles")
    var nationalTitles: Int,

    @ColumnInfo(name = "international_titles")
    var internationalTitles: Int,

    @ColumnInfo(name = "foundation_year")
    var foundationYear: Int,

    @ColumnInfo(name = "stadium_name")
    var stadiumName: String,

    @ColumnInfo(name = "stadium_capacity")
    var stadiumCapacity: Int,

    @ColumnInfo(name = "url_avatar")
    var urlAvatar: String,

    @ColumnInfo(name = "national_cups")
    var nationalCups: Int,

    @ColumnInfo(name = "location")
    var location: String

) : Parcelable {
}