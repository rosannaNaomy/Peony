package com.example.peony.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import androidx.room.Insert
import com.example.peony.database.entities.MedicationData
import com.example.peony.database.entities.TempMedData
import com.example.peony.database.entities.UserEntity
import com.example.peony.database.entities.relations.UserwithMedications

@Dao
interface AppDao {

    @Query("SELECT * FROM user")
    fun getUser(): List<UserEntity>

    @Delete
    fun deleteUser(userEntity: UserEntity)

    //insert a record, which will take user input
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMed(medicationData: MedicationData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTempMed(tempMedData: TempMedData)

    @Query("DELETE FROM tempMed")
    suspend fun deleteAllTempMeds()

    @Query("SELECT * FROM tempMed")
    fun getTempMeds(): LiveData<List<TempMedData>>

    @Query("DELETE FROM med")
    suspend fun deleteAllMeds()

    @Transaction
    @Query("SELECT * FROM user WHERE userName = :userName")
    fun getUsersWithMedications(userName: String): LiveData<List<UserwithMedications>>

    @Query("SELECT * FROM med")
    fun getMeds(): LiveData<List<MedicationData>>

}