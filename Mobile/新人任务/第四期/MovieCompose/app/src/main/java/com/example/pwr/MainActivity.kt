package com.example.pwr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.pwr.ui.theme.PwrTheme
import com.example.pwr.data.local.AppDatabase
import com.example.pwr.repository.MovieRepository
import com.example.pwr.viewmodel.SearchViewModel
import com.example.pwr.viewmodel.DetailViewModel
import com.example.pwr.viewmodel.HistoryViewModel
import com.example.pwr.ui.MovieNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PwrTheme {
                val appContext = applicationContext
                val db = remember { AppDatabase.getInstance(appContext) }
                val repository = remember { MovieRepository(db.movieDao()) }
                val searchVm = remember { SearchViewModel(repository) }
                val detailVm = remember { DetailViewModel(repository) }
                val historyVm = remember { HistoryViewModel(repository) }

                MovieNavGraph(
                    searchViewModel = searchVm,
                    detailViewModel = detailVm,
                    historyViewModel = historyVm
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) { /* ...existing code... */ }
