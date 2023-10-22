package com.example.application.interfaces

import com.example.application.entities.CurrentResponse
import com.example.application.entities.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

public interface CurrentApi {
    @GET
    suspend fun getCurrent(
        @Url url: String,
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("lang") lang: String
    ) : Response<CurrentResponse>

    @GET
    suspend fun getForecast(
        @Url url: String,
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String,
        @Query("lang") lang: String
    ) : Response<ForecastResponse>
}