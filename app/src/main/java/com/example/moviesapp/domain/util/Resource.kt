package com.example.moviesapp.domain.util

sealed class Resource<T>(
    val status: Status,
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T, errorMessage: String? = null) :
        Resource<T>(Status.SUCCESS, data, errorMessage)

    class Loading<T>(data: T? = null, errorMessage: String? = null) :
        Resource<T>(Status.LOADING, data, errorMessage)

    class Error<T>(data: T? = null, errorMessage: String) :
        Resource<T>(Status.ERROR, data, errorMessage)
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}