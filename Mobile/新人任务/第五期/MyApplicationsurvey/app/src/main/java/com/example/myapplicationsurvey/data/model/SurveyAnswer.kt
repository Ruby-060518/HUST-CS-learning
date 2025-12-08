package com.example.myapplicationsurvey.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SurveyResponse(
    val surveyId: String,
    val respondentName: String? = null,
    val avatarUri: String? = null,
    val birthday: String? = null,
    val pronouns: String? = null,
    val listeningHabits: List<String> = emptyList(),
    val musicVibes: List<String> = emptyList(),
    val favoriteArtist: String? = null,
    val answers: Map<String, List<String>> = emptyMap()
)
