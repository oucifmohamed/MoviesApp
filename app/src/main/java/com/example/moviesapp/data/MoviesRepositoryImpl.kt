package com.example.moviesapp.data

import com.example.moviesapp.data.util.MovieDetailsDtoMapper
import com.example.moviesapp.data.util.MoviesListDtoMapper
import com.example.moviesapp.domain.MoviesRepository
import com.example.moviesapp.domain.model.MovieDetails
import com.example.moviesapp.domain.model.MoviesList
import com.example.moviesapp.domain.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val movieService: MoviesServiceApi,
    private val moviesListDtoMapper: MoviesListDtoMapper,
    private val movieDetailsDtoMapper: MovieDetailsDtoMapper
) : MoviesRepository {
    override suspend fun getTrendMovies(page: Int): Resource<MoviesList> {

        return try {
            val result = movieService.getTrendMovies(page = page)
            Resource.Success(moviesListDtoMapper.mapToDomainModel(result))
        }catch (e: Exception) {
            Resource.Error(errorMessage = e.message!!)
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Resource<MovieDetails> {

        return try {
            val result = movieService.getMovieDetails(movieId)
            Resource.Success(movieDetailsDtoMapper.mapToDomainModel(result))
        }catch (e: Exception) {
            Resource.Error(errorMessage = e.message!!)
        }
    }
}