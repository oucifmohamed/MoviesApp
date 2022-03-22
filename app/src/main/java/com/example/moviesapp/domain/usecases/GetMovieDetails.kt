package com.example.moviesapp.domain.usecases

import com.example.moviesapp.domain.MoviesRepository
import com.example.moviesapp.domain.model.MovieDetails
import com.example.moviesapp.domain.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieDetails @Inject constructor(
    private val repository: MoviesRepository
){

    suspend fun invoke(movieId: Int): Resource<MovieDetails> {
        return repository.getMovieDetails(movieId)
    }
}