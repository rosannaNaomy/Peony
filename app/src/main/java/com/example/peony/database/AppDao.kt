package com.example.peony.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.peony.model.RepositoryData

@Dao
interface AppDao {

    //pass entity, table name user, order by desc, new records will show on top
    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getRecords(): List<UserEntity>

    @Delete
    fun deleteUser(userEntity: UserEntity)

    @Query("DELETE FROM user")
    fun deleteAllUsers()

    //insert a record, which will take user input
    @Insert
    fun insertRecord(userEntity: UserEntity)

    @Query("SELECT * FROM med ORDER BY id DESC")
    fun getMedData(): LiveData<List<RepositoryData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedData(repositoryData: RepositoryData)

    @Query("DELETE FROM med")
    fun deleteAllMedData()

}