package com.example.peony.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.peony.database.AppDao
import com.example.peony.database.AppDatabase
import com.example.peony.database.PreferencesHelper
import com.example.peony.network.RetroServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APPModule {
    val BASE_URL ="https://api.fda.gov/drug/label.json/"
    val api_key = "Fpg5yQVsUeHIbd2kP6tsw8zA2POUHDk5mdJ50caX"

    @Provides
    @Singleton
    @Named("api_key")
    fun provideApiKey(): String{
        return api_key
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesHelper(
        @ApplicationContext context: Context) = PreferencesHelper(context)


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
    fun getDao(appDB: AppDatabase): AppDao {
        return appDB.getDao()
    }
}


