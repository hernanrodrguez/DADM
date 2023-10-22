package com.example.application.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class City(
    var name: String = "",
    var region: String = "",
    var country: String = "",
    var localTime: String = "",
    var lastUpdated: String = "",
    var tempC: Number = 0,
    var feelsLike: Number = 0,
    var humidity: Number = 0,
    var pressure: Number = 0,
    var precipitation: Number = 0,
    var cloudCover: Number = 0,
    var uvIndex: Number = 0,
    var isDay: Number = 0,
    var condition: String = "",
    var conditionImgUrl: String = "",
    var windKph: Number = 0,
    var windDir: String = ""
) : Parcelable {
    fun setCurrent(location: Location?, current: Current?){
        if(location != null){
            this.name = location.name
            this.region = location.region
            this.country = location.country
            this.localTime = location.localtime
        } else {
            this.name = ""
            this.region = ""
            this.country = ""
            this.localTime = ""
        }

        if(current != null){
            this.lastUpdated = current.lastUpdated
            this.tempC = current.tempC
            this.feelsLike = current.feelslikeC
            this.humidity = current.humidity
            this.pressure = current.pressureMb
            this.precipitation = current.precipMm
            this.cloudCover = current.cloud
            this.uvIndex = current.uv
            this.isDay = current.isDay
            this.condition = current.condition.text
            this.conditionImgUrl = current.condition.icon
            this.windKph = current.windKph
            this.windDir = current.windDir
        } else {
            this.lastUpdated = ""
            this.tempC = 0
            this.feelsLike = 0
            this.humidity = 0
            this.pressure = 0
            this.precipitation = 0
            this.cloudCover = 0
            this.uvIndex = 0
            this.isDay = 0
            this.condition = ""
            this.conditionImgUrl = ""
            this.windKph = 0
            this.windDir = ""
        }
    }
}