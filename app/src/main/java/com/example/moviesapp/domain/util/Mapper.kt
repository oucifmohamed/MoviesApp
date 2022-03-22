package com.example.moviesapp.domain.util

interface Mapper<T, DomainModel> {

    fun mapToDomainModel(model: T): DomainModel
}