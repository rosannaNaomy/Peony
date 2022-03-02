package com.example.peony.di

import android.app.Application
import android.content.Context
import com.example.peony.database.AppDao
import com.example.peony.database.AppDatabase
import com.example.peony.database.MedDatabase
import com.example.peony.network.RetroServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APPModule {

    val BASE_URL = "https://api.fda.gov/drug"

    @Provides
    @Singleton
    fun getMedDatabase(context: Application): MedDatabase{
        return MedDatabase.getMedDBInstance(context)
    }

    @Singleton
    @Provides
    fun getDaoMed(medDB: MedDatabase): AppDao{
        return medDB.getAPPDao()
    }

    @Provides
    @Singleton
    fun getRetroService(retrofit: Retrofit): RetroServiceInterface{
        return retrofit.create(RetroServiceInterface::class.java)
    }

    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides //returns instance of database, pass context
    fun getAppDB(context: Application):AppDatabase{
        return AppDatabase.getAppDB(context)
    }

    @Singleton
    @Provides
    fun getDao(appDB: AppDatabase): AppDao{
        return appDB.getDao()
    }
}


