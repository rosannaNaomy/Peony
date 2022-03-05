package com.example.peony.database

import androidx.room.TypeConverter
import com.example.peony.model.Openfda
import com.example.peony.model.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToResultList(data: String?): List<Result>?{
        val listType: Type = object: TypeToken<List<Result>?>() {}.type
        return Gson().fromJson<List<Result>>(data, listType)
    }

    @TypeConverter
    fun resultListToString(objects: List<Result>?): String?{
        return Gson().toJson(objects)
    }

    @TypeConverter
    fun stringToOpenfdaList(data: String?): Openfda?{
        val listType: Type = object: TypeToken<Openfda?>() {}.type
        return Gson().fromJson<Openfda>(data, listType)
    }

    @TypeConverter
    fun openfdaListToString(objects: Openfda?): String?{
        return Gson().toJson(objects)
    }

}
