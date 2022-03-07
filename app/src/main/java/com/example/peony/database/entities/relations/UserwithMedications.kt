package com.example.peony.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.peony.database.entities.MedicationData
import com.example.peony.database.entities.UserEntity

//1-N Relation
data class UserwithMedications (
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "userName",
        entityColumn = "userName"
    )
    val medicationList: List<MedicationData>
)