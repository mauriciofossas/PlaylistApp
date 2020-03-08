package com.example.cse438.cse438_assignment2.Network

import com.example.cse438.cse438_assignment2.Data.ChartPayload
import com.example.cse438.cse438_assignment2.Data.FullTrack
import com.example.cse438.cse438_assignment2.Data.Payload
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrackInterface {
    //Look up tracks by name or artist
    @GET("search/track")
    suspend fun getSongs(@Query("q") q: String) : Response<Payload>

    @GET("chart")
    suspend fun getTopSongs() : Response<ChartPayload>

    @GET("track/{Id}")
    suspend fun getSingleTrack(@Path("Id") id : String) : Response<FullTrack>

}