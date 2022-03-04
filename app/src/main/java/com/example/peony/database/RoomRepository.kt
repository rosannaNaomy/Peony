package com.example.peony.database

import android.util.Log
import com.example.peony.database.entities.MedicationData
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
                                         private val appDao: AppDao
) {

    //query
//    fun getRecords(): List<UserEntity>{
//        return appDao.getRecords()
//    }

    //get med relations with user
//    fun getUserWithUserMeds(userName: String): List<UserWithMedication>{
//        return appDao.getUserWithUserMeds(userName)
//    }

    suspend fun getMeds(): List<MedicationData>{
        return appDao.getMeds()
    }

    //to insert user to our room db
//    fun insertUser(userEntity: UserEntity){
//        appDao.insertUser(userEntity)
//    }

    suspend fun insertMed(medicationData: MedicationData){
         appDao.insertMed(medicationData)
    }

//    fun deleteUser(userEntity: UserEntity){
//        appDao.deleteUser(userEntity)
//    }

//    fun deleteAllUsers(){
//        appDao.deleteAllUsers()
//    }

    fun makeApiCall(query: String){
        CoroutineScope(IO).launch{
            Log.d("RoomRepository", "Inside Api call, Query: $query")
            val response = retroServiceInterface.getDataFromAPI(apiKey, query)
            val jObjError = JSONObject(response.errorBody()!!.string())
            Log.d("RoomRepository", "Error Response: ${response.errorBody()} $jObjError")
            if(response.isSuccessful){
                Log.d("RoomRepository", "apiCallSuccessful: $response")
                appDao.deleteAllMeds()
                response.body()?.items?.forEach {
                insertMed(it)
                }
            }
        }
    }
}