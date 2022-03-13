package com.example.peony.database

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class PreferencesHelper constructor(context: Context){

    private val preferences = context.getSharedPreferences("user", MODE_PRIVATE)
    private val USER_KEY = "USER"
    private var preferenceListener: PreferenceListener? = null

    fun addUser(name: String){
//        val userName = preferences.getString(USER_KEY, null)
        preferences.edit().putString(USER_KEY, name).apply()
    }

    fun getUser() = preferences.getString(USER_KEY, null)

    fun subscribeToOnNameChange(listener: PreferenceListener){
        this.preferenceListener = listener
        preferences.registerOnSharedPreferenceChangeListener(sharedPreferencesListener)
    }

    fun unsubscribeToOnNameChange(){
        this.preferenceListener = null
        preferences.unregisterOnSharedPreferenceChangeListener(sharedPreferencesListener)
    }

    private val sharedPreferencesListener = SharedPreferences.OnSharedPreferenceChangeListener { preferences, key ->
        if(key == USER_KEY){
            preferenceListener?.OnNameChange(preferences.getString(key, null))
        }
    }
}