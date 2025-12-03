package com.example.myapplicationsurvey.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "survey_responses")
data class SurveyResponseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val surveyId: String,
    val answersJson: String,   // 所有回答序列化成 JSON
    val timestamp: Long
)
