@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pwr.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.History
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pwr.viewmodel.SearchViewModel
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onOpenDetail: (String) -> Unit,
    onOpenHistory: () -> Unit
) {
    val movies by viewModel.movies.collectAsState()
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMsg
    val listState = rememberLazyListState()
    val coroutine = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        TopAppBar(
            title = { Text("Movie Compose") },
            actions = {
                IconButton(onClick = { onOpenHistory() }) {
                    Icon(Icons.Filled.History, contentDescription = "History")
                }
            }
        )

        Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = viewModel.query,
                onValueChange = { viewModel.onQueryChanged(it) },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Search movies (e.g. batman)") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.startSearch() }) {
                Text("Search")
            }
        }

        if (error != null) {
            Text(text = error, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
                itemsIndexed(movies) { index, item ->
                    MovieRow(item = item, onClick = {
                        viewModel.recordViewFromItem(item)
                        onOpenDetail(item.imdbID)
                    })
                    Divider()
                    // trigger next page when approaching bottom
                    if (index >= movies.size - 1) {
                        // side-effect to fetch next page
                        LaunchedEffect(key1 = movies.size) {
                            viewModel.fetchNextPage()
                        }
                    }
                }

                // show a little space at bottom
                item {
                    Spacer(modifier = Modifier.height(64.dp))
                }
            }

            if (isLoading && movies.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            // small floating button to go top
            if (listState.firstVisibleItemIndex > 5) {
                FloatingActionButton(
                    onClick = {
                        coroutine.launch {
                            listState.animateScrollToItem(0)
                        }
                    },
                    modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
                ) {
                    Icon(Icons.Filled.ArrowUpward, contentDescription = "Top")
                }
            }
        }
    }
}

@Composable
fun MovieRow(item: com.example.pwr.viewmodel.UiMovieItem, onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(8.dp), verticalAlignment = Alignment.CenterVertically) {

        AsyncImage(
            model = item.poster,
            contentDescription = item.title,
            modifier = Modifier.size(90.dp).padding(end = 8.dp)
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.title ?: "Unknown title", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = item.year ?: "Unknown year", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
