package com.example.pwr.data.remote



import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    // Search: http://www.omdbapi.com/?s=batman&page=1&apikey=xxx
    @GET("/")
    suspend fun searchMovies(
        @Query("s") query: String,
        @Query("page") page: Int,
        @Query("apikey") apiKey: String
    ): Response<SearchResponse>

    // Get details by imdbID: http://www.omdbapi.com/?i=tt3896198&apikey=xxx&plot=full
    @GET("/")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String,
        @Query("plot") plot: String = "full"
    ): Response<MovieDetailResponse>
}
