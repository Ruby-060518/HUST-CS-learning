package com.example.myapplicationsurvey.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplicationsurvey.data.model.SurveyResponse
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
@Composable
fun HomeScreen(responses: List<SurveyResponse>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(responses) { r ->
            Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                androidx.compose.foundation.layout.Column(modifier = Modifier.padding(12.dp)) {
                    Text("Name: ${r.respondentName ?: "Anonymous"}")
                    Text("Favorite Artist: ${r.favoriteArtist ?: "-"}")
                    Text("Listening Habits: ${r.listeningHabits.joinToString()}")
                }
            }
        }
    }
}