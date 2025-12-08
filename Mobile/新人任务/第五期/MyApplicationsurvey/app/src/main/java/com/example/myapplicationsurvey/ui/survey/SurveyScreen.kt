package com.example.myapplicationsurvey.ui.survey

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationsurvey.data.model.MultiChoiceQuestion
import com.example.myapplicationsurvey.data.model.SingleChoiceQuestion
import com.example.myapplicationsurvey.data.model.TextQuestion
import com.example.myapplicationsurvey.ui.survey.components.QuestionMultiChoice
import com.example.myapplicationsurvey.ui.survey.components.QuestionSingleChoice
import com.example.myapplicationsurvey.ui.survey.components.QuestionText
import com.example.myapplicationsurvey.ui.theme.Cream
import com.example.myapplicationsurvey.viewmodel.SurveyViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurveyScreen(viewModel: SurveyViewModel, onFinish: () -> Unit) {
    val survey = viewModel.currentSurvey.value ?: return
    val pageIndex = viewModel.pageIndex.value
    val page = survey.pages.getOrNull(pageIndex) ?: return
    val qIndex = viewModel.questionIndex.value
    val q = page.questions.getOrNull(qIndex) ?: return

    // 计算整个问卷的总题数，以及当前是第几题（从 1 开始）
    val totalQuestions = remember(survey) {
        survey.pages.sumOf { it.questions.size }
    }
    val currentQuestionIndex = remember(survey, pageIndex, qIndex) {
        var index = 0
        survey.pages.forEachIndexed { pIdx, p ->
            if (pIdx < pageIndex) {
                index += p.questions.size
            }
        }
        // 当前页之前的题目数量 + 当前页内的题目索引，再 +1 变为 1-based 序号
        index + qIndex + 1
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Cream
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // 顶部显示当前题号与总题数
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$currentQuestionIndex of $totalQuestions",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                // 右侧可以将来放一个关闭按钮，这里先用占位避免引入新颜色/图标
                Spacer(modifier = Modifier.width(24.dp))
            }

            // 顶部进度条：基于题目进度而不是页数
            LinearProgressIndicator(
                progress = currentQuestionIndex.toFloat() / totalQuestions.coerceAtLeast(1)
                    .toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
            )

            Spacer(Modifier.height(24.dp))

            // 问题主文案卡片
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = page.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(Modifier.height(12.dp))

            // 副标题，根据题型简单推断
            val subtitle = when (q) {
                is MultiChoiceQuestion -> "Select all that apply."
                is SingleChoiceQuestion -> "Select one option."
                else -> ""
            }
            if (subtitle.isNotEmpty()) {
                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // 题目内容区域
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                when (q) {
                    is SingleChoiceQuestion -> {
                        val selected =
                            viewModel.responseState.value.answers["pronouns"]?.firstOrNull()
                                .takeIf { q.id == "gender" }
                                ?: viewModel.responseState.value.answers[q.id]?.firstOrNull()
                        QuestionSingleChoice(q, selected) { sel ->
                            viewModel.updateResponse {
                                val key = if (q.id == "gender") "pronouns" else q.id
                                copy(answers = answers + (key to listOf(sel)))
                            }
                        }
                    }

                    is MultiChoiceQuestion -> {
                        val pid = q.id.lowercase()
                        val pprompt = q.prompt.lowercase()
                        val isFavGenres =
                            pid == "music_vibes" || pprompt.contains("what music genres do you like")
                        val baseKey = when (q.id) {
                            "listening_habits" -> "listenHabit"
                            else -> q.id
                        }
                        val key = if (isFavGenres) "genres" else baseKey
                        val selected = viewModel.responseState.value.answers[key] ?: emptyList()
                        QuestionMultiChoice(q, selected) { opt ->
                            val cur = viewModel.responseState.value.answers[key] ?: emptyList()
                            val new = if (cur.contains(opt)) cur - opt else cur + opt
                            viewModel.updateResponse { copy(answers = answers + (key to new)) }
                        }
                    }

                    is TextQuestion -> {
                        val currentMap = viewModel.responseState.value.answers
                        val mappedKey = when (q.id) {
                            "birthday" -> "birthdayEpochDay"
                            "favorite_artist" -> "favoriteArtist"
                            else -> q.id
                        }
                        val value = currentMap[mappedKey]?.firstOrNull()
                            ?: currentMap[q.id]?.firstOrNull()
                            ?: ""
                        val isBirthday = mappedKey == "birthdayEpochDay" ||
                                q.id.lowercase().contains("birthday") ||
                                q.id.lowercase().contains("birth") ||
                                q.id.lowercase().contains("date")
                        if (isBirthday) {
                            var showPicker by remember { mutableStateOf(false) }
                            val dateState = rememberDatePickerState()
                            val displayText = remember(value) {
                                val v = value.toLongOrNull()
                                if (v != null) {
                                    val millis = v * 86_400_000L
                                    val fmt = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                                    fmt.format(Date(millis))
                                } else "Select date"
                            }

                            // 参考图片的标题 + 副标题
                            Text(
                                text = "When is your birthday?",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                            )
                            Text(
                                text = "Select your date of birth",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            // 卡片式日期选择框
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(24.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Button(
                                        onClick = { showPicker = true },
                                        modifier = Modifier.fillMaxWidth(),
                                    ) {
                                        Text(displayText)
                                    }
                                }
                            }

                            Spacer(Modifier.height(24.dp))

                            if (showPicker) {
                                DatePickerDialog(
                                    onDismissRequest = { showPicker = false },
                                    confirmButton = {
                                        Button(onClick = {
                                            val selectedEpochMillis = dateState.selectedDateMillis
                                            if (selectedEpochMillis != null) {
                                                val epochDay = selectedEpochMillis / 86_400_000L
                                                viewModel.updateResponse {
                                                    copy(
                                                        answers = answers + (mappedKey to listOf(
                                                            epochDay.toString()
                                                        ))
                                                    )
                                                }
                                            }
                                            showPicker = false
                                        }) { Text("OK") }
                                    },
                                    dismissButton = {
                                        Button(onClick = { showPicker = false }) { Text("Cancel") }
                                    }
                                ) {
                                    DatePicker(state = dateState)
                                }
                            }
                        } else {
                            // 非生日文本题，保持原有实现
                            QuestionText(q, value) { new ->
                                viewModel.updateResponse {
                                    copy(answers = answers + (mappedKey to listOf(new)))
                                }
                            }
                        }
                    }
                }
            }

            // 底部：左侧 Previous TextButton + 右侧大按钮 Next/Submit
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { viewModel.prevQuestion() },
                    enabled = !(pageIndex == 0 && qIndex == 0),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Previous")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (!(pageIndex == survey.pages.lastIndex && qIndex == page.questions.lastIndex)) {
                            viewModel.nextQuestion()
                        } else {
                            viewModel.submitResponse(onFinish)
                        }
                    },
                    modifier = Modifier
                        .weight(2f)
                        .height(52.dp),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = if (!(pageIndex == survey.pages.lastIndex && qIndex == page.questions.lastIndex)) {
                            "Next"
                        } else {
                            "Submit"
                        }
                    )
                }
            }
        }
    }
}
