package com.example.pwr.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pwr.data.remote.SearchItem
import com.example.pwr.repository.MovieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UiMovieItem(
    val imdbID: String,
    val title: String?,
    val year: String?,
    val poster: String?
)

class SearchViewModel(private val repository: MovieRepository) : ViewModel() {

    var query by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMsg by mutableStateOf<String?>(null)
    private val _movies = MutableStateFlow<List<UiMovieItem>>(emptyList())
    val movies: StateFlow<List<UiMovieItem>> = _movies

    private var currentPage = 1
    private var totalResults = Int.MAX_VALUE
    private var isRequestInFlight = false
    private var lastQuery = ""
    private var searchJob: Job? = null

    fun onQueryChanged(new: String) {
        query = new
    }

    fun startSearch() {
        if (query.isBlank()) return
        // reset
        currentPage = 1
        totalResults = Int.MAX_VALUE
        _movies.value = emptyList()
        lastQuery = query
        fetchNextPage()
    }

    fun fetchNextPage() {
        if (isRequestInFlight) return
        // check if we've loaded all
        val loaded = _movies.value.size
        if (loaded >= totalResults) return

        isRequestInFlight = true
        isLoading = true
        errorMsg = null

        viewModelScope.launch {
            try {
                val resp = repository.searchMovies(lastQuery, currentPage)
                if (resp.isSuccessful) {
                    val body = resp.body()
                    if (body?.Response == "True" && body.Search != null) {
                        val items = body.Search.map { s ->
                            UiMovieItem(imdbID = s.imdbID ?: "", title = s.Title, year = s.Year, poster = s.Poster)
                        }
                        _movies.value = _movies.value + items
                        totalResults = body.totalResults?.toIntOrNull() ?: Int.MAX_VALUE
                        currentPage++
                    } else {
                        // no result or error
                        if (currentPage == 1) {
                            _movies.value = emptyList()
                            errorMsg = body?.Error ?: "No results"
                        }
                    }
                } else {
                    errorMsg = "Network error: ${resp.code()}"
                }
            } catch (e: Exception) {
                errorMsg = "Exception: ${e.message}"
            } finally {
                isRequestInFlight = false
                isLoading = false
            }
        }
    }

    // called when user clicks item to record history (we keep lightweight record from list)
    fun recordViewFromItem(item: UiMovieItem) {
        viewModelScope.launch {
            repository.recordViewFromSearchItem(
                com.example.pwr.data.remote.SearchItem(
                    Title = item.title,
                    Year = item.year,
                    imdbID = item.imdbID,
                    Type = null,
                    Poster = item.poster
                )
            )
        }
    }
}
