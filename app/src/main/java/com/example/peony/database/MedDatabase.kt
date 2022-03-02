package com.example.peony.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.peony.model.RepositoryData

@Database(entities = [RepositoryData::class], version = 1, exportSchema = false)
abstract class MedDatabase: RoomDatabase() {

    abstract fun getAPPDao(): AppDao

    companion object{
        private var db_instance: MedDatabase? = null

        fun getMedDBInstance(context: Context): MedDatabase{
            if(db_instance == null){
                db_instance = Room.databaseBuilder(
                    context.applicationContext, MedDatabase::class.java, "MedDB")
                    .allowMainThreadQueries()
                    .build()
            }
            return db_instance!!
        }
    }

}