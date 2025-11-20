package com.example.pwr.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pwr.data.remote.MovieDetailResponse
import com.example.pwr.repository.MovieRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MovieRepository) : ViewModel() {
    var detail by mutableStateOf<MovieDetailResponse?>(null)
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf<String?>(null)

    fun loadDetail(imdbId: String) {
        isLoading.value = true
        error.value = null
        viewModelScope.launch {
            try {
                val resp = repository.getMovieDetail(imdbId)
                if (resp.isSuccessful) {
                    val body = resp.body()
                    if (body?.Response == "True") {
                        detail = body
                        // record view
                        repository.recordViewFromDetail(body)
                    } else {
                        error.value = body?.Error ?: "No detail"
                    }
                } else {
                    error.value = "Network error: ${resp.code()}"
                }
            } catch (e: Exception) {
                error.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }
}
