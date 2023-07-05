package com.movieApplication.data.movie.repository

import androidx.paging.PagingData
import com.movieApplication.data.movie.catalog.mapper.MoviesCatalogMapper
import com.movieApplication.data.movie.datasource.MovieDataSource
import com.movieApplication.domain.movie.catalog.MoviesCatalogItem
import com.movieApplication.domain.movie.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dataSource: MovieDataSource,
    private val moviesCatalogMapper: MoviesCatalogMapper
) : MovieRepository {

    override fun getPopularMovies(): Flow<PagingData<MoviesCatalogItem>> {
        val remoteMoviesCatalogResponse = dataSource.getPopularMovies()
        return moviesCatalogMapper(remoteMoviesCatalogResponse = remoteMoviesCatalogResponse)
    }

    override fun getTopRatedMovies(): Flow<PagingData<MoviesCatalogItem>> {
        val remoteMoviesCatalogResponse = dataSource.getTopRatedMovies()
        return moviesCatalogMapper(remoteMoviesCatalogResponse = remoteMoviesCatalogResponse)
    }

    override fun getUpcomingMovies(): Flow<PagingData<MoviesCatalogItem>> {
        val remoteMoviesCatalogResponse = dataSource.getUpcomingMovies()
        return moviesCatalogMapper(remoteMoviesCatalogResponse = remoteMoviesCatalogResponse)
    }

    override fun getNowPlayingMovies(): Flow<PagingData<MoviesCatalogItem>> {
        val remoteMoviesCatalogResponse = dataSource.getNowPlayingMovies()
        return moviesCatalogMapper(remoteMoviesCatalogResponse = remoteMoviesCatalogResponse)
    }

}