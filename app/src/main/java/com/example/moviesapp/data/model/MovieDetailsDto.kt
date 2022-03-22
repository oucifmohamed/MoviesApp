package com.example.moviesapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsDto(

    @SerializedName("id")
    val id: Int,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("budget")
    val budget: Long,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val vote_average: Float
)