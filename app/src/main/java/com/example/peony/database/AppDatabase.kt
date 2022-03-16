package com.example.peony.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.peony.database.entities.MedicationData
import com.example.peony.database.entities.TempMedData
import com.example.peony.database.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
        MedicationData::class,
        TempMedData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    //define dao inside database
    abstract fun getDao(): AppDao

    companion object{
        private var dbInstance: AppDatabase? = null

        fun getAppDB(context: Context): AppDatabase{
            synchronized(this){
               return dbInstance ?: Room.databaseBuilder(
                   context.applicationContext,
                   AppDatabase::class.java,
                   "APPDB"
               ).build().also{
                   dbInstance = it
               }
            }
        }
    }
}