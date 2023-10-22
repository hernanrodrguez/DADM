package com.example.application.entities

class City(
    var name: String = "",
    var region: String = "",
    var country: String = "",
    var localTime: String = "",
    var lastUpdated: String = "",
    var tempC: Number = 0,
    var feelsLike: Number = 0,
    var humidity: Number = 0,
    var isDay: Number = 0,
    var condition: String = "",
    var conditionImgUrl: String = "",
    var windKph: Number = 0,
    var windDir: String = ""
) {
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
            this.isDay = 0
            this.condition = ""
            this.conditionImgUrl = ""
            this.windKph = 0
            this.windDir = ""
        }
    }
}