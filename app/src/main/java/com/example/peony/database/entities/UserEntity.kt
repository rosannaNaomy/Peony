package com.example.peony.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val userName: String,
 //   val medication: String
//    @ColumnInfo(name = "userName") val name: String,
//    @ColumnInfo(name = "id") val id: Int = 0
)