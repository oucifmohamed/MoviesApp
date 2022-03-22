package com.example.moviesapp.di

import com.example.moviesapp.data.MoviesRepositoryImpl
import com.example.moviesapp.domain.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMoviesRepository(repositoryImpl: MoviesRepositoryImpl): MoviesRepository
}