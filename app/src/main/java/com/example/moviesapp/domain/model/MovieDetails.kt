package com.example.moviesapp.domain.model

data class MovieDetails(
    val id: Int,
    val adult: Boolean,
    val budget: Long,
    val poster_path: String,
    val release_date: String,
    val runtime: Int,
    val overview: String,
    val title: String,
    val vote_average: Float,
)
