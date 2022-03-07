package com.example.peony.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.peony.database.RoomRepository
import com.example.peony.database.entities.MedicationData
import com.example.peony.database.entities.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel //access our repository in our viewmodel
class MedicineActivityViewModel @Inject constructor(private val repository: RoomRepository): ViewModel(){

    //define viewmodel
    //lateinit var medData: MutableLiveData<List<MedicationData>>

    //initialize livedata
//    init{
//        medData = MutableLiveData()
//       // GlobalScope.launch {  loadRecords() }
//    }

    fun getRecordsObserver(): LiveData<List<MedicationData>>{
        return repository.getMeds()
    }

    //call function from repository
//    suspend fun loadRecords(){
//        val list = repository.getMeds()
//        medData.postValue(list)
//    }

    //after inserting make db call
    suspend fun insertMed(medicationData: MedicationData){
        repository.insertMed(medicationData)
    }

    suspend fun insertUser(userEntity: UserEntity){
        repository.insertUser(userEntity)
    }

    fun makeApiCall(query: String) {
        repository.makeApiCall(query)
    }
}