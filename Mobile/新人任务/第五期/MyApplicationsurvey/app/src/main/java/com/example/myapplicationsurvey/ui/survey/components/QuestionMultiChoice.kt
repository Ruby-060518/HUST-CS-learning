package com.example.myapplicationsurvey.ui.survey.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplicationsurvey.data.model.MultiChoiceQuestion

@Composable
fun QuestionMultiChoice(q: MultiChoiceQuestion, selected: List<String>, onToggle: (String) -> Unit) {Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
    Text(q.prompt)
    q.options.forEach { opt ->
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Checkbox(checked = selected.contains(opt), onCheckedChange = { onToggle(opt) })
            Spacer(modifier = Modifier.width(8.dp))
            Text(opt)
        }
    }
}
}