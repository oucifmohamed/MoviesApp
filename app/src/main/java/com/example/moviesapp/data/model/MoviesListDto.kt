package com.example.moviesapp.data.model

import com.google.gson.annotations.SerializedName

data class MoviesListDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieDto>
)
