package com.example.moviesapp.domain

import com.example.moviesapp.domain.model.MovieDetails
import com.example.moviesapp.domain.model.MoviesList
import com.example.moviesapp.domain.util.Resource

interface MoviesRepository {

    suspend fun getTrendMovies(page: Int): Resource<MoviesList>

    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetails>
}