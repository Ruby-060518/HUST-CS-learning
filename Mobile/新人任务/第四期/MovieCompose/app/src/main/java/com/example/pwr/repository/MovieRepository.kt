package com.example.pwr.repository



import com.example.pwr.data.local.MovieDao
import com.example.pwr.data.local.MovieEntity
import com.example.pwr.data.remote.RetrofitProvider
import com.example.pwr.data.remote.SearchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception

class MovieRepository(private val movieDao: MovieDao) {

    private val api = RetrofitProvider.api
    private val apiKey = RetrofitProvider.apiKey

    suspend fun searchMovies(query: String, page: Int): Response<com.example.pwr.data.remote.SearchResponse> {
        return api.searchMovies(query, page, apiKey)
    }

    suspend fun getMovieDetail(imdbId: String): Response<com.example.pwr.data.remote.MovieDetailResponse> {
        return api.getMovieDetail(imdbId, apiKey)
    }

    suspend fun recordViewFromSearchItem(item: SearchItem) = withContext(Dispatchers.IO) {
        val entity = MovieEntity(
            imdbID = item.imdbID ?: "",
            title = item.Title,
            year = item.Year,
            poster = item.Poster,
            lastViewedAt = System.currentTimeMillis()
        )
        movieDao.upsert(entity)
    }

    suspend fun recordViewFromDetail(detail: com.example.pwr.data.remote.MovieDetailResponse) = withContext(Dispatchers.IO) {
        val entity = MovieEntity(
            imdbID = detail.imdbID ?: "",
            title = detail.Title,
            year = detail.Year,
            poster = detail.Poster,
            lastViewedAt = System.currentTimeMillis()
        )
        movieDao.upsert(entity)
    }

    fun getHistoryFlow() = movieDao.getHistoryFlow()

    suspend fun clearHistory() = withContext(Dispatchers.IO) {
        movieDao.clearAll()
    }
}
