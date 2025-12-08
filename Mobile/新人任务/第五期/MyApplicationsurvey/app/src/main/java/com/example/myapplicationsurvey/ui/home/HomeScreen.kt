package com.example.myapplicationsurvey.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplicationsurvey.data.model.SurveyResponse
import com.example.myapplicationsurvey.ui.theme.Cream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(responses: List<SurveyResponse>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Cream
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(responses) { r ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    androidx.compose.foundation.layout.Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        val name = r.answers["name"]?.firstOrNull() ?: "Anonymous"
                        val birthdayEpochDay =
                            r.answers["birthdayEpochDay"]?.firstOrNull()?.toLongOrNull()
                        val birthdayText = birthdayEpochDay?.let {
                            val millis = it * 86_400_000L // epochDay -> millis since 1970-01-01
                            val fmt = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            fmt.format(Date(millis))
                        } ?: "-"
                        val pronouns = r.answers["pronouns"]?.firstOrNull() ?: "-"
                        val habits = r.answers["listenHabit"] ?: emptyList()
                        val genres = r.answers["genres"] ?: emptyList()
                        val artist = r.answers["favoriteArtist"]?.firstOrNull() ?: "-"

                        Text("Name: $name")
                        Text("Birthday: $birthdayText")
                        Text("Pronouns: $pronouns")
                        Text("Listening Habits: ${habits.joinToString()}")
                        Text("Favorite Genres: ${genres.joinToString()}")
                        Text("Favorite Artist: $artist")
                    }
                }
            }
        }
    }
}