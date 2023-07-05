package com.movieApplication.domain.movie.repository

import androidx.paging.PagingData
import com.movieApplication.domain.movie.catalog.MoviesCatalogItem
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<PagingData<MoviesCatalogItem>>

    fun getTopRatedMovies(): Flow<PagingData<MoviesCatalogItem>>

    fun getUpcomingMovies(): Flow<PagingData<MoviesCatalogItem>>

    fun getNowPlayingMovies(): Flow<PagingData<MoviesCatalogItem>>
}