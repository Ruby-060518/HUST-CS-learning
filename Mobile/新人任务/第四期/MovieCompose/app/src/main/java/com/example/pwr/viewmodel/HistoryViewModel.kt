package com.example.pwr.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pwr.data.local.MovieEntity
import com.example.pwr.repository.MovieRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HistoryViewModel(repository: MovieRepository) : ViewModel() {

    val historyFlow: StateFlow<List<MovieEntity>> =
        repository.getHistoryFlow()
            .map { list -> list } // identity, can map if needed
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun clearHistory(repository: MovieRepository) {
        viewModelScope.launch {
            repository.clearHistory()
        }
    }
}
