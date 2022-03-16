package com.example.peony.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.peony.model.Openfda
import com.example.peony.model.Result
import java.io.Serializable

@Entity(tableName = "tempMed")
data class TempMedData (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "opendfda")val opendfda: Openfda,
    @ColumnInfo(name = "result")val result: List<Result?>,
    val userName: String
    ) : Serializable