package com.example.moviesapp.data.util

import com.example.moviesapp.data.model.MovieDto
import com.example.moviesapp.data.model.MoviesListDto
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MoviesList
import com.example.moviesapp.domain.util.Mapper

class MoviesListDtoMapper: Mapper<MoviesListDto, MoviesList>{

    override fun mapToDomainModel(model: MoviesListDto): MoviesList {
        return MoviesList(
            page = model.page,
            movies = mapToDomainList(model.movies)
        )
    }

    private fun mapToDomainList(initial: List<MovieDto>): List<Movie> {
        return initial.map {
            Movie(
                id = it.id,
                poster_path = it.poster_path,
                release_date = it.release_date,
                title = it.title
            )
        }
    }
}