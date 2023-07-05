package com.movieApplication.data.movie.datasource

import androidx.paging.PagingData
import com.movieApplication.data.movie.catalog.model.RemoteMoviesCatalogItem
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {

    fun getPopularMovies(): Flow<PagingData<RemoteMoviesCatalogItem>>

    fun getTopRatedMovies(): Flow<PagingData<RemoteMoviesCatalogItem>>

    fun getUpcomingMovies(): Flow<PagingData<RemoteMoviesCatalogItem>>

    fun getNowPlayingMovies(): Flow<PagingData<RemoteMoviesCatalogItem>>

}