package com.example.myapplicationsurvey.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplicationsurvey.data.model.SurveyEntity
import com.example.myapplicationsurvey.data.model.SurveyResponseEntity

@Database(entities = [SurveyEntity::class, SurveyResponseEntity::class], version = 3)
@TypeConverters(com.example.myapplicationsurvey.data.database.Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun surveyDao(): SurveyDao
    abstract fun surveyResponseDao(): SurveyResponseDao
}