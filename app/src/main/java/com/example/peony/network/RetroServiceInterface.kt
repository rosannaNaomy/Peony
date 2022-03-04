package com.example.peony.network

import com.example.peony.model.MedData
import retrofit2.Response
import retrofit2.http.*

interface RetroServiceInterface {

    @GET("?api_key")
    suspend fun getDataFromAPI(@QueryMap filter: HashMap<String, String>): Response<MedData>
}