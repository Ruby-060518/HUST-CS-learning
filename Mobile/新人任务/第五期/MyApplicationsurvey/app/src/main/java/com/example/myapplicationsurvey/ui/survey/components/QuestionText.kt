package com.example.myapplicationsurvey.ui.survey.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplicationsurvey.data.model.TextQuestion

@Composable
fun QuestionText(q: TextQuestion, value: String, onChange: (String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(q.prompt)
        OutlinedTextField(value = value, onValueChange = onChange, placeholder = {
            Text(q.placeholder)
        }, modifier = Modifier.fillMaxWidth())
    }
}