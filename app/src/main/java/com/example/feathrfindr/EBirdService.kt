package com.example.feathrfindr

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface eBirdService {

    @GET("hotspot/recent")
    fun getHotspot(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("distance") distance: Double,
        @Header("X-EBIRD-API-KEY") apiKey: String = "m0gehcar4vag"
    ): Call<List<Hotspot>>
}
