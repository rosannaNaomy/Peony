package com.example.peony.database

import javax.inject.Inject

//inject constructor, pass appDao
class RoomRepository @Inject constructor(private val appDao: AppDao) {

    //query
    fun getRecords(): List<UserEntity>{
        return appDao.getRecords()
    }

    //to insert to our room db
    fun insertRecord(userEntity: UserEntity){
        appDao.insertRecord(userEntity)
    }

}