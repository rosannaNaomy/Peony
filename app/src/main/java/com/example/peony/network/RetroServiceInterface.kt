package com.example.peony.network

import com.example.peony.model.RepositoriesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {

    @GET
    fun getDataFromAPI(@Query("q")query: String): Call<RepositoriesList>
}