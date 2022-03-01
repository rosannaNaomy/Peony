package com.example.peony.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDao {

    //pass entity, table name user, order by desc, new records will show on top
    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getRecords(): List<UserEntity>

    //insert a record, which will take user input
    @Insert
    fun insertRecord(userEntity: UserEntity)

}