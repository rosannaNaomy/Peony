package com.example.peony.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.peony.database.RoomRepository
import com.example.peony.database.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel //access our repository in our viewmodel
class MedicineActivityViewModel @Inject constructor(private val repository: RoomRepository): ViewModel(){

    //define viewmodel
    lateinit var userData: MutableLiveData<List<UserEntity>>

    //initialize livedata
    init{
        userData = MutableLiveData()
        loadRecords()
    }

    fun getRecordsObserver(): MutableLiveData<List<UserEntity>>{
        return userData
    }

    //call function from repository
    fun loadRecords(){
        val list = repository.getRecords()
        userData.postValue(list)
    }

    //after inserting make db call
    fun insertRecord(userEntity: UserEntity){
        repository.insertRecord(userEntity)
        loadRecords()
    }
}