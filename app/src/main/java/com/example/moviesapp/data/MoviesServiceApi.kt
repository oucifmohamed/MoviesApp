package com.example.moviesapp.data

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.model.MovieDetailsDto
import com.example.moviesapp.data.model.MoviesListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesServiceApi {

    @GET("discover/movie")
    suspend fun getTrendMovies(
        @Query("api_key") api_key: String = BuildConfig.api_key,
        @Query("page") page: Int
    ): MoviesListDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = BuildConfig.api_key
    ): MovieDetailsDto
}