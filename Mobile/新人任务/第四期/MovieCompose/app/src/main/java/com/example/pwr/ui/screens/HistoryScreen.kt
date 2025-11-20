@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pwr.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pwr.data.local.MovieEntity
import com.example.pwr.viewmodel.HistoryViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryScreen(
    historyViewModel: HistoryViewModel,
    onBack: () -> Unit,
    onOpenDetail: (String) -> Unit
) {
    val history by historyViewModel.historyFlow.collectAsState()
    Scaffold(topBar = {
        TopAppBar(title = { Text("History") }, navigationIcon = {
            IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, contentDescription = "Back") }
        })
    }) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            if (history.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Text("No history yet")
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                    items(history) { item ->
                        HistoryRow(item = item, onClick = { onOpenDetail(item.imdbID) })
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryRow(item: MovieEntity, onClick: () -> Unit) {
    val sdf = remember { SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()) }
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(8.dp)) {
        AsyncImage(model = item.poster, contentDescription = item.title, modifier = Modifier.size(80.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(item.title ?: "Unknown", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(item.year ?: "", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Viewed: ${sdf.format(Date(item.lastViewedAt))}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
