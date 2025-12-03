package com.example.myapplicationsurvey.ui.survey

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplicationsurvey.data.model.*
import com.example.myapplicationsurvey.ui.survey.components.QuestionSingleChoice
import com.example.myapplicationsurvey.ui.survey.components.QuestionMultiChoice
import com.example.myapplicationsurvey.ui.survey.components.QuestionText
import com.example.myapplicationsurvey.viewmodel.SurveyViewModel
@Composable
fun SurveyScreen(viewModel: SurveyViewModel, onFinish: () -> Unit) {
    val survey = viewModel.currentSurvey.value ?: return
    val pageIndex = viewModel.pageIndex.value
    val page = survey.pages.getOrNull(pageIndex) ?: return
    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        Text(page.title)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(page.questions.size) { idx ->
                val q = page.questions[idx]
                when (q) {
                    is SingleChoiceQuestion -> {
                        val selected = viewModel.responseState.value.answers[q.id]?.firstOrNull()
                        QuestionSingleChoice(q, selected) { sel -> viewModel.updateResponse { copy(answers =
                            answers + (q.id to listOf(sel))) } }
                    }
                    is MultiChoiceQuestion -> {
                        val selected = viewModel.responseState.value.answers[q.id] ?: emptyList()
                        QuestionMultiChoice(q, selected) { opt ->
                            val cur = viewModel.responseState.value.answers[q.id] ?: emptyList()
                            val new = if (cur.contains(opt)) cur - opt else cur + opt
                            viewModel.updateResponse { copy(answers = answers + (q.id to new)) }
                        }
                    }
                    is TextQuestion -> {
                        val value = viewModel.responseState.value.answers[q.id]?.firstOrNull() ?: ""
                        QuestionText(q, value) { new -> viewModel.updateResponse { copy(answers = answers +
                                (q.id to listOf(new))) } }
                    }
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement =
            androidx.compose.foundation.layout.Arrangement.SpaceBetween) {
            if (pageIndex > 0) Button(onClick = { viewModel.pageIndex.value = pageIndex - 1 }) {
                Text("Previous") }
            if (pageIndex < survey.pages.size - 1) Button(onClick = { viewModel.pageIndex.value =
                pageIndex + 1 }) { Text("Next") }
            else Button(onClick = { viewModel.submitResponse(onFinish) }) { Text("Submit") }
        }
    }
}
