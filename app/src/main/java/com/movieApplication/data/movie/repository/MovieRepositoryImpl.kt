package com.movieApplication.data.movie.repository

import androidx.paging.PagingData
import com.movieApplication.data.movie.catalog.mapper.MoviesCatalogMapper
import com.movieApplication.data.movie.datasource.MovieDataSource
import com.movieApplication.data.movie.details.mapper.MovieDetailsCastMapper
import com.movieApplication.data.movie.details.mapper.MovieDetailsMapper
import com.movieApplication.domain.movie.catalog.MoviesCatalogItem
import com.movieApplication.domain.movie.details.MovieDetailsResult
import com.movieApplication.domain.movie.details.MoviesDetailsCastResult
import com.movieApplication.domain.movie.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dataSource: MovieDataSource,
    private val moviesCatalogMapper: MoviesCatalogMapper,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val movieDetailsCastMapper: MovieDetailsCastMapper
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

    override fun getSearchedMovies(searchedMovie: String): Flow<PagingData<MoviesCatalogItem>> {
        val remoteMoviesCatalogResponse = dataSource.getSearchedMovies(searchedMovie)
        return moviesCatalogMapper(remoteMoviesCatalogResponse = remoteMoviesCatalogResponse)
    }

    override suspend fun getMovieDetails(movieId: String): MovieDetailsResult {
        val remoteMovieDetailsResponse = dataSource.getMovieDetails(movieId = movieId)
        return movieDetailsMapper(remoteMovieDetailsResponse = remoteMovieDetailsResponse)
    }

    override suspend fun getMovieDetailsCast(movieId: String): MoviesDetailsCastResult {
        val remoteMovieDetailsCastResponse = dataSource.getMovieDetailsCast(movieId = movieId)
        return movieDetailsCastMapper(remoteMovieDetailsCastItemsResponse = remoteMovieDetailsCastResponse)
    }

}