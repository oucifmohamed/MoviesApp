package com.example.moviesapp.presentation.movie_details_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.model.MovieDetails
import com.example.moviesapp.domain.usecases.GetMovieDetails
import com.example.moviesapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetails: GetMovieDetails
): ViewModel(){

    private val _movieDetails = MutableLiveData<Resource<MovieDetails>>()
    val movieDetails: LiveData<Resource<MovieDetails>> = _movieDetails

    fun getMovieDetails(movieId: Int) = viewModelScope.launch {

        _movieDetails.value = Resource.Loading()
        val movieDetails = getMovieDetails.invoke(movieId)
        _movieDetails.value = movieDetails
    }
}