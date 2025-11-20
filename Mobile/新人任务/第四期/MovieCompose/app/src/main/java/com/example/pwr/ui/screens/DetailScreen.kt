@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pwr.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pwr.viewmodel.DetailViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api

@Composable
fun DetailScreen(imdbId: String, viewModel: DetailViewModel, onBack: () -> Unit) {
    LaunchedEffect(imdbId) {
        viewModel.loadDetail(imdbId)
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Detail") }, navigationIcon = {
            IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, contentDescription = "Back") }
        })
    }) { padding ->
        val detail = viewModel.detail
        val loading = viewModel.isLoading.value
        val error = viewModel.error.value

        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            if (loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (error != null) {
                Text(text = error, color = MaterialTheme.colorScheme.error, modifier = Modifier.align(Alignment.Center))
            } else if (detail != null) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
                ) {
                    AsyncImage(model = detail.Poster, contentDescription = detail.Title, modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp))
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(detail.Title ?: "", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Year: ${detail.Year ?: "N/A"}", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Runtime: ${detail.Runtime ?: "N/A"}")
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Genre: ${detail.Genre ?: "N/A"}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Plot", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(detail.Plot ?: "N/A")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Director: ${detail.Director ?: "N/A"}")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Actors: ${detail.Actors ?: "N/A"}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("IMDB Rating: ${detail.imdbRating ?: "N/A"}")
                }
            } else {
                Text("No detail yet", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
