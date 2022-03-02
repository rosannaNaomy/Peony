package com.example.peony.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "med")
data class RepositoryData (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id: Int = 0,
    @ColumnInfo(name = "name")val brand_name: String?,
    @ColumnInfo(name = "description")val description: String?)