package com.example.peony.view

import androidx.lifecycle.ViewModel
import com.example.peony.database.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DisplayMedicineActivityViewModel @Inject constructor(private val roomRepository: RoomRepository): ViewModel() {

//    fun getAllRepositoryList(): LiveData<List<MedicationData>> {
//        return roomRepository.getMedData()
//    }
//
//    fun makeApiCall() {
//        roomRepository.makeApiCall("")
//    }
}