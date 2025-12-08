package com.example.myapplicationsurvey.data.json

import kotlinx.serialization.json.Json
import com.example.myapplicationsurvey.data.model.Survey

object SurveyJsonExporter {
    fun toJson(survey: Survey): String = Json.encodeToString(Survey.serializer(), survey)
}