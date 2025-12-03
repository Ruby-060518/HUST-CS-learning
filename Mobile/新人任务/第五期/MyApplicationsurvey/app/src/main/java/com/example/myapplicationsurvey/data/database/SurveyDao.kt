package com.example.myapplicationsurvey.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplicationsurvey.data.model.SurveyEntity
import com.example.myapplicationsurvey.data.model.SurveyResponseEntity

@Dao
interface SurveyDao {

    @Insert
    suspend fun insertSurvey(entity: SurveyEntity)

    @Query("SELECT * FROM surveys")
    suspend fun getAllSurveys(): List<SurveyEntity>
}

@Dao
interface SurveyResponseDao {

    @Insert
    suspend fun insertResponse(entity: SurveyResponseEntity)

    @Query("SELECT * FROM survey_responses")
    suspend fun getAllResponses(): List<SurveyResponseEntity>
}
