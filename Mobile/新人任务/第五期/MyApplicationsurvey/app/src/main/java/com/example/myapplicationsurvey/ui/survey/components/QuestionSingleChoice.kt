package com.example.myapplicationsurvey.ui.survey.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplicationsurvey.data.model.SingleChoiceQuestion
@Composable
fun QuestionSingleChoice(q: SingleChoiceQuestion, selected: String?, onSelect: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(q.prompt)
        q.options.forEach { opt ->
            Row(modifier = Modifier.fillMaxWidth().clickable { onSelect(opt) }.padding(8.dp)) {
                RadioButton(selected = selected == opt, onClick = { onSelect(opt) })
                Spacer(modifier = Modifier.width(8.dp))
                Text(opt)
            }
        }
    }
}
