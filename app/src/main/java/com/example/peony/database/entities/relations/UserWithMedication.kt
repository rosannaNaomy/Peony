package com.example.peony.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.peony.database.entities.MedicationData
import com.example.peony.database.entities.UserEntity

//1-N Relation
data class UserWithMedication (
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "UserName",
        entityColumn = "UserName"
    )
    val meds: List<MedicationData>
)