package com.example.myapplicationsurvey.viewmodel

import android.app.Application
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationsurvey.data.model.Survey
import com.example.myapplicationsurvey.data.model.SurveyResponse
import com.example.myapplicationsurvey.data.repository.SurveyRepository
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class SurveyViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = SurveyRepository.getInstance(app)
    val currentSurvey = mutableStateOf<Survey?>(null)
    val pageIndex = mutableStateOf(0)
    val responseState = mutableStateOf(SurveyResponse(surveyId = ""))
    val savedSurveys = mutableStateOf<List<Survey>>(emptyList())
    val savedResponses = mutableStateOf<List<SurveyResponse>>(emptyList())

    fun loadAll() { viewModelScope.launch { savedSurveys.value = repo.getAllSurveys();
        savedResponses.value = repo.getAllResponses() } }

    fun importSurveyFromJson(jsonText: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            try { val survey = Json.decodeFromString(Survey.serializer(), jsonText); repo.saveSurvey(survey);
                savedSurveys.value = repo.getAllSurveys(); onResult(true, null) }
            catch (e: Exception) { onResult(false, e.localizedMessage) }
        } }

    fun startSurvey(survey: Survey) { currentSurvey.value = survey; pageIndex.value = 0;
        responseState.value = SurveyResponse(surveyId = survey.id) }
    fun updateResponse(update: SurveyResponse.() -> SurveyResponse) { responseState.value =
        responseState.value.update() }
    fun submitResponse(onComplete: () -> Unit = {}) { viewModelScope.launch {
        repo.saveResponse(responseState.value); loadAll(); onComplete() } }
    fun readClipboardText(): String? {
        val cm = getApplication<Application>()
            .getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val clip = cm.primaryClip ?: return null
        val item = clip.getItemAt(0) ?: return null

        val text = item.coerceToText(getApplication())?.toString()

        return text
    }
}
