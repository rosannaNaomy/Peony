package com.example.peony.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import androidx.room.Insert
import com.example.peony.database.entities.MedicationData
import com.example.peony.database.entities.UserEntity
import com.example.peony.database.entities.relations.UserWithMedication

@Dao
interface AppDao {

    //pass entity, table name user, order by desc, new records will show on top
//    @Query("SELECT * FROM user ORDER BY id DESC")
//    fun getRecords(): List<UserEntity>

//    @Delete
//    fun deleteUser(userEntity: UserEntity)
//
//    @Query("DELETE FROM user")
//    fun deleteAllUsers()

    //insert a record, which will take user input
//    @Insert(onConflict = onConflictStrategy.REPLACE)
//    fun insertUser(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMed(medicationData: MedicationData)

    @Query("DELETE FROM med")
    suspend fun deleteAllMeds()

//    @Transaction
//    @Query("SELECT * FROM user WHERE userName = :userName")
//    suspend fun getUserAndMedWithUserName(userName: String): List<UserWithMedication>

    @Query("SELECT * FROM med")
    fun getMeds(): LiveData<List<MedicationData>>

//    @Transaction
//    @Query("SELECT * FROM user WHERE userName = :userName")
//    fun getUserWithUserMeds(userName: String): List<UserWithMedication>

}