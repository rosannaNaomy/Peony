package com.example.peony.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "med")
data class MedicationData (
    @PrimaryKey(autoGenerate = false)
    val brand_name: String,
   // @ColumnInfo(name = "name")val brand_name: String?,
   // @ColumnInfo(name = "id")val id: Int = 0,
//    @ColumnInfo(name = "description")val description: String,
    @ColumnInfo(name = "generic_name")val generic_name: String)