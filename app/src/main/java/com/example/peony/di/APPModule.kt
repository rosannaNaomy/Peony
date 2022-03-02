package com.example.peony.di

import android.content.Context
import com.example.peony.database.AppDao
import com.example.peony.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APPModule {

    @Singleton
    @Provides //returns instance of database, pass context
    fun getAppDB(context: Context):AppDatabase{
        return AppDatabase.getAppDB(context)
    }

    @Singleton
    @Provides
    fun getDao(appDB: AppDatabase): AppDao{
        return appDB.getDao()
    }
}


