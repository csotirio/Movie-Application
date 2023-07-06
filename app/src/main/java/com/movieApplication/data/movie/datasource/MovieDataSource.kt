package com.movieApplication.data.movie.datasource

import androidx.paging.PagingData
import com.movieApplication.data.movie.catalog.model.RemoteMoviesCatalogItem
import com.movieApplication.data.movie.details.model.RemoteMovieDetailsCastItemsResponse
import com.movieApplication.data.movie.details.model.RemoteMovieDetailsResponse
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {

    fun getPopularMovies(): Flow<PagingData<RemoteMoviesCatalogItem>>

    fun getTopRatedMovies(): Flow<PagingData<RemoteMoviesCatalogItem>>

    fun getUpcomingMovies(): Flow<PagingData<RemoteMoviesCatalogItem>>

    fun getNowPlayingMovies(): Flow<PagingData<RemoteMoviesCatalogItem>>

    fun getSearchedMovies(searchedMovie: String): Flow<PagingData<RemoteMoviesCatalogItem>>

    suspend fun getMovieDetails(movieId: String): RemoteMovieDetailsResponse

    suspend fun getMovieDetailsCast(movieId: String): RemoteMovieDetailsCastItemsResponse
}