package com.example.myapplicationsurvey.data.repository

import android.content.Context
import androidx.room.Room
import com.example.myapplicationsurvey.data.database.AppDatabase
import com.example.myapplicationsurvey.data.model.*
import com.example.myapplicationsurvey.data.model.SurveyEntity
import com.example.myapplicationsurvey.data.model.SurveyResponseEntity
import kotlinx.serialization.json.Json

class SurveyRepository private constructor(context: Context) {
    private val db = Room.databaseBuilder(context, AppDatabase::class.java, "survey-db").build()
    private val surveyDao = db.surveyDao()
    private val responseDao = db.surveyResponseDao()

    suspend fun saveSurvey(survey: Survey) {
        val json = Json.encodeToString(Survey.serializer(), survey)
        // SurveyEntity expects: id, title, description, questionsJson
        val description = "" // minimal fix: Survey model has no description field
        val entity = SurveyEntity(
            id = survey.id,
            title = survey.title,
            description = description,
            questionsJson = json
        )
        surveyDao.insertSurvey(entity)
    }

    suspend fun getAllSurveys(): List<Survey> = surveyDao.getAllSurveys().map {
        Json.decodeFromString(Survey.serializer(), it.questionsJson)
    }

    suspend fun saveResponse(response: SurveyResponse) {
        val json = Json.encodeToString(SurveyResponse.serializer(), response)
        // SurveyResponseEntity expects: id, surveyId, answersJson, timestamp
        val entity = SurveyResponseEntity(
            id = 0,
            surveyId = response.surveyId,
            answersJson = json,
            timestamp = System.currentTimeMillis()
        )
        responseDao.insertResponse(entity)
    }

    suspend fun getAllResponses(): List<SurveyResponse> = responseDao.getAllResponses().map {
        Json.decodeFromString(SurveyResponse.serializer(), it.answersJson)
    }

    companion object {
        @Volatile
        private var INSTANCE: SurveyRepository? = null
        fun getInstance(context: Context): SurveyRepository = INSTANCE ?: synchronized(this) {
            INSTANCE ?: SurveyRepository(context.applicationContext).also { INSTANCE = it }
        }
    }
}