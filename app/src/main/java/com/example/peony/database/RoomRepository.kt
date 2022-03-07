package com.example.peony.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.peony.database.entities.MedicationData
import com.example.peony.database.entities.UserEntity
import com.example.peony.network.RetroServiceInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Named


//inject constructor, pass appDao
class RoomRepository @Inject constructor(private val retroServiceInterface: RetroServiceInterface,
                                         @Named("api_key") private val apiKey: String,
                                         private val appDao: AppDao)
{
    //query
    fun getUser(): List<UserEntity>{
        return appDao.getUser()
    }

    fun getMeds(): LiveData<List<MedicationData>>{
        return appDao.getMeds()
    }

    //to insert user to our room db
    suspend fun insertUser(userEntity: UserEntity){
        appDao.insertUser(userEntity)
    }

    suspend fun insertMed(medicationData: MedicationData){
         appDao.insertMed(medicationData)
    }

    fun makeApiCall(query: String){
        val searchFieldQuery = "openfda.brand_name:\"$query\"" //This could become a user input searchable field, right now it is static
        val filter = HashMap<String, String>() //Query map
        filter["api_key"] = apiKey
        filter["search"] = searchFieldQuery
        filter["limit"] = "5"

        CoroutineScope(IO).launch{
            Log.d("RoomRepository", "Inside Api call, Query: $query")
            val response = retroServiceInterface.getDataFromAPI(filter)
//            val jObjError = JSONObject(response.errorBody()!!.string()) // returns response errorbody message
            Log.d("RoomRepository", "Error Response: ${response.errorBody()}")
            if(response.isSuccessful){
                appDao.deleteAllMeds()
                Log.d("RoomRepository", "apiCallSuccessful: $response")
                Log.d("RoomRepository", "Check for data: ${response.body()!!.results.size}")
//                Log.d("RoomRepository", "Check for data: ${response.body()!!.results[0].drug_interactions[0]}")
                Log.d("RoomRepository", "Check for data: ${response.body()!!.results[1].openfda.brand_name}")
                response.body()?.results?.forEach {
                    insertMed(MedicationData(result = response.body()!!.results, opendfda = it.openfda, userName = "None"))
                }
            }
        }
    }
}