package com.example.peony.network

import com.example.peony.model.MedicationList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetroServiceInterface {

   // ("?api_key=&search=openfda.brand_name:")
   // ("?api_key=&search=field:term")
   // @GET("?api_key=&search=openfda.brand_name=")
    @GET("?api_key=&search=openfda.brand_name=")
    suspend fun getDataFromAPI(
       @Query("api_key") api_key: String,
       @Query("brand_name") brand_name: String
   ): Response<MedicationList>
}