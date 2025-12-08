package com.example.myapplicationsurvey.data.json

import kotlinx.serialization.json.Json
import com.example.myapplicationsurvey.data.model.Survey

object SurveyJsonImporter {
    fun fromJson(text: String): Survey = Json.decodeFromString(Survey.serializer(), text)
}