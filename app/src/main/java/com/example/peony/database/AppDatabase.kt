package com.example.peony.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    //define dao inside database
    abstract fun getDao(): AppDao

    companion object{
        private var dbInstance: AppDatabase? = null

        //return instance of database
        fun getAppDB(context: Context): AppDatabase{
            if (dbInstance == null){
                dbInstance = Room.databaseBuilder<AppDatabase>(context.applicationContext,
                    AppDatabase::class.java, "APPDB")
                    .allowMainThreadQueries()
                    .build()
            }
            return dbInstance!!
        }
    }
}