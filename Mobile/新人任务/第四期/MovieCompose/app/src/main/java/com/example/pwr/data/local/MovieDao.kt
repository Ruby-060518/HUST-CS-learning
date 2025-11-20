package com.example.pwr.data.local



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: MovieEntity)

    @Query("SELECT * FROM history ORDER BY lastViewedAt DESC")
    fun getHistoryFlow(): Flow<List<MovieEntity>>

    @Query("DELETE FROM history")
    suspend fun clearAll()
}
