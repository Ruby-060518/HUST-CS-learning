package com.example.pwr.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class MovieEntity(
    @PrimaryKey val imdbID: String,
    val title: String?,
    val year: String?,
    val poster: String?,
    val lastViewedAt: Long // store epoch millis for ordering
)
