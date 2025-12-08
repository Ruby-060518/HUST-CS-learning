package com.example.myapplicationsurvey.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromStringList(value: List<String>?): String = Json.encodeToString(value ?: emptyList())

    @TypeConverter
    fun toStringList(value: String): List<String> = Json.decodeFromString(value)
}
