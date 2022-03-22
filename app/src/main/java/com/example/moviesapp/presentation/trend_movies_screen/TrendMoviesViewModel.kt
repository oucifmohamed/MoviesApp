package com.example.moviesapp.presentation.trend_movies_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MoviesList
import com.example.moviesapp.domain.usecases.GetTrendMovies
import com.example.moviesapp.domain.util.Resource
import com.example.moviesapp.domain.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendMoviesViewModel @Inject constructor(
    private val getTrendMovies: GetTrendMovies
): ViewModel(){

    private val _currentMoviesList = MutableLiveData<Resource<MoviesList>>()
    val currentMoviesList: LiveData<Resource<MoviesList>> = _currentMoviesList
    var pageNumber = 1

    fun getTrendMovies(page: Int) = viewModelScope.launch {
        _currentMoviesList.value = Resource.Loading()
        val moviesList = getTrendMovies.invoke(page)
        _currentMoviesList.value = moviesList

        if(moviesList.status == Status.SUCCESS)
            pageNumber = moviesList.data!!.page
    }
}