package com.example.application.interfaces

import com.example.application.entities.CurrentResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

public interface CurrentApi {
    @GET
    suspend fun getCurrent(
        @Url url: String,
        @Query("key") key: String,
        @Query("q") q: String
    ) : Response<CurrentResponse>
}