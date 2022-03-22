package com.example.moviesapp.domain.usecases

import com.example.moviesapp.domain.MoviesRepository
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MoviesList
import com.example.moviesapp.domain.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTrendMovies @Inject constructor(
    private val moviesRepository: MoviesRepository
){

    suspend fun invoke(page: Int): Resource<MoviesList> {
        return moviesRepository.getTrendMovies(page)
    }
}