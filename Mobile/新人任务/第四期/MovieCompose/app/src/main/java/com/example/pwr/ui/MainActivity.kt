package com.example.pwr.ui



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pwr.data.local.AppDatabase
import com.example.pwr.repository.MovieRepository
import com.example.pwr.ui.theme.MovieComposeTheme
import com.example.pwr.viewmodel.DetailViewModel
import com.example.pwr.viewmodel.HistoryViewModel
import com.example.pwr.viewmodel.SearchViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {

    // Simple ViewModelFactory to inject repository
    class MainFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return when {
                modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(repository) as T
                modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository) as T
                modelClass.isAssignableFrom(HistoryViewModel::class.java) -> HistoryViewModel(repository) as T
                else -> throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create DB and repo
        val db = AppDatabase.getInstance(applicationContext)
        val repository = MovieRepository(db.movieDao())

        val factory = MainFactory(repository)

        setContent {
            MovieComposeTheme {
                // create viewmodels with factory
                val searchViewModel: SearchViewModel = viewModel(factory = factory)
                val detailViewModel: DetailViewModel = viewModel(factory = factory)
                val historyViewModel: HistoryViewModel = viewModel(factory = factory)

                MovieNavGraph(
                    searchViewModel = searchViewModel,
                    detailViewModel = detailViewModel,
                    historyViewModel = historyViewModel
                )
            }
        }
    }
}
