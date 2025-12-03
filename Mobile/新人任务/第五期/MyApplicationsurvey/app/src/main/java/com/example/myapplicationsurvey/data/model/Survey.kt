package com.example.myapplicationsurvey.data.model
import kotlinx.serialization.Serializable
@Serializable
data class Survey(
    val id: String,
    val title: String,
    val pages: List<SurveyPage>
)
@Serializable
data class SurveyPage(
    val id: String,
    val title: String,
    val questions: List<Question>
)
