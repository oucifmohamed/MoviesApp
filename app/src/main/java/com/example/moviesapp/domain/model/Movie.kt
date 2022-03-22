package com.example.moviesapp.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val poster_path: String,
    val release_date: String,
    val title: String,
)
