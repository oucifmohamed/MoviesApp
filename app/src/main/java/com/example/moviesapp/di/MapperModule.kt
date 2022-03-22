package com.example.moviesapp.di

import com.example.moviesapp.data.util.MovieDetailsDtoMapper
import com.example.moviesapp.data.util.MoviesListDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideMovieMapper(): MoviesListDtoMapper = MoviesListDtoMapper()

    @Provides
    @Singleton
    fun provideMovieDetailsMapper(): MovieDetailsDtoMapper = MovieDetailsDtoMapper()
}