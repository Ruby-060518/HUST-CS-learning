package com.example.myapplicationsurvey.data.repository

import android.content.Context
import androidx.room.Room
import com.example.myapplicationsurvey.data.database.AppDatabase
import com.example.myapplicationsurvey.data.model.*
import com.example.myapplicationsurvey.data.model.SurveyEntity
import com.example.myapplicationsurvey.data.model.SurveyResponseEntity
import kotlinx.serialization.json.Json

class SurveyRepository private constructor(context: Context) {
    private val db = Room.databaseBuilder(context, AppDatabase::class.java, "survey-db")
        .fallbackToDestructiveMigration()
        .build()
    private val surveyDao = db.surveyDao()
    private val responseDao = db.surveyResponseDao()

    suspend fun saveSurvey(survey: Survey) {
        val json = Json.encodeToString(Survey.serializer(), survey)
        // SurveyEntity expects: id, title, description, questionsJson
        val description = "" // 保留字段，占位
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

    // 将答案 Map 映射为列式实体
    suspend fun saveResponse(response: SurveyResponse) {
        val a = response.answers
        fun first(key: String): String? = a[key]?.firstOrNull()
        fun boolOf(listKey: String, vararg anyOf: String): Boolean? {
            val list = a[listKey] ?: return null
            val norm = list.map { it.lowercase() }
            val targets = anyOf.map { it.lowercase() }
            return norm.any { s -> targets.any { t -> s.contains(t) } }
        }

        val entity = SurveyResponseEntity(
            id = 0,
            surveyId = response.surveyId,
            name = first("name"),
            avatarUri = first("avatar"),
            birthdayEpochDay = first("birthdayEpochDay")?.toLongOrNull(),
            pronouns = first("pronouns"),
            listenHabitCommute = boolOf("listenHabit", "commute"),
            listenHabitBedtime = boolOf("listenHabit", "bedtime"),
            listenHabitWorkout = boolOf("listenHabit", "workout"),
            listenHabitStudy = boolOf("listenHabit", "study"),
            // 15 个风格
            likeJazz = boolOf("genres", "jazz"),
            likeRock = boolOf("genres", "rock"),
            likePop = boolOf("genres", "pop"),
            likeHipHop = boolOf("genres", "hip hop", "hip-hop", "rap"),
            likeClassical = boolOf("genres", "classical"),
            likeRnB = boolOf("genres", "r&b", "rhythm and blues"),
            likeCountry = boolOf("genres", "country"),
            likeEdm = boolOf("genres", "electronic", "dance", "edm"),
            likeBlues = boolOf("genres", "blues"),
            likeSoul = boolOf("genres", "soul"),
            likeFunk = boolOf("genres", "funk"),
            likeReggae = boolOf("genres", "reggae"),
            likeDisco = boolOf("genres", "disco"),
            likeFolk = boolOf("genres", "folk"),
            likeMetal = boolOf("genres", "metal"),
            favoriteArtist = first("favoriteArtist"),
            timestamp = System.currentTimeMillis()
        )
        responseDao.insertResponse(entity)
    }

    // 从列式实体还原为 UI 使用的 SurveyResponse（保持现有界面取数方式）
    suspend fun getAllResponses(): List<SurveyResponse> = responseDao.getAllResponses().map { e ->
        val map = mutableMapOf<String, List<String>>()
        e.name?.let { map["name"] = listOf(it) }
        e.avatarUri?.let { map["avatar"] = listOf(it) }
        e.birthdayEpochDay?.let { map["birthdayEpochDay"] = listOf(it.toString()) }
        e.pronouns?.let { map["pronouns"] = listOf(it) }
        val habits = listOfNotNull(
            if (e.listenHabitCommute == true) "Commute" else null,
            if (e.listenHabitBedtime == true) "Bedtime" else null,
            if (e.listenHabitWorkout == true) "Workout" else null,
            if (e.listenHabitStudy == true) "Study" else null,
        )
        if (habits.isNotEmpty()) map["listenHabit"] = habits
        val genres = mutableListOf<String>()
        if (e.likePop == true) genres += "Pop"
        if (e.likeRock == true) genres += "Rock"
        if (e.likeHipHop == true) genres += "Hip Hop / Rap"
        if (e.likeRnB == true) genres += "R&B (Rhythm and Blues)"
        if (e.likeCountry == true) genres += "Country"
        if (e.likeEdm == true) genres += "Electronic / Dance / EDM"
        if (e.likeJazz == true) genres += "Jazz"
        if (e.likeBlues == true) genres += "Blues"
        if (e.likeClassical == true) genres += "Classical"
        if (e.likeSoul == true) genres += "Soul"
        if (e.likeFunk == true) genres += "Funk"
        if (e.likeReggae == true) genres += "Reggae"
        if (e.likeDisco == true) genres += "Disco"
        if (e.likeFolk == true) genres += "Folk"
        if (e.likeMetal == true) genres += "Metal"
        if (genres.isNotEmpty()) map["genres"] = genres
        e.favoriteArtist?.let { map["favoriteArtist"] = listOf(it) }
        SurveyResponse(surveyId = e.surveyId, answers = map)
    }

    companion object {
        @Volatile
        private var INSTANCE: SurveyRepository? = null
        fun getInstance(context: Context): SurveyRepository = INSTANCE ?: synchronized(this) {
            INSTANCE ?: SurveyRepository(context.applicationContext).also { INSTANCE = it }
        }
    }
}