package com.example.moviesapp.data.util

import com.example.moviesapp.data.model.MovieDetailsDto
import com.example.moviesapp.domain.model.MovieDetails
import com.example.moviesapp.domain.util.Mapper

class MovieDetailsDtoMapper: Mapper<MovieDetailsDto, MovieDetails>{

    override fun mapToDomainModel(model: MovieDetailsDto): MovieDetails {
        return MovieDetails(
            id = model.id,
            adult = model.adult,
            budget = model.budget,
            poster_path = model.poster_path,
            release_date = model.release_date,
            runtime = model.runtime,
            overview = model.overview,
            title = model.title,
            vote_average = model.vote_average
        )
    }
}