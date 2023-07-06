package com.movieApplication.framework.movie.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.movieApplication.BuildConfig
import com.movieApplication.data.movie.catalog.model.RemoteMoviesCatalogItem
import com.movieApplication.data.movie.datasource.MovieDataSource
import com.movieApplication.data.movie.details.model.RemoteMovieDetailsCastItemsResponse
import com.movieApplication.data.movie.details.model.RemoteMovieDetailsResponse
import com.movieApplication.framework.movie.api.MovieApi
import com.movieApplication.framework.movie.pager.RemoteNowPlayingMoviesPaging
import com.movieApplication.framework.movie.pager.RemotePopularMoviesPaging
import com.movieApplication.framework.movie.pager.RemoteSearchedMoviesPaging
import com.movieApplication.framework.movie.pager.RemoteTopRatedMoviesPaging
import com.movieApplication.framework.movie.pager.RemoteUpcomingMoviesPaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val api: MovieApi
) : MovieDataSource {

    override fun getPopularMovies(): Flow<PagingData<RemoteMoviesCatalogItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ), pagingSourceFactory = {
                RemotePopularMoviesPaging(
                    api = api
                )
            }).flow
    }

    override fun getTopRatedMovies(): Flow<PagingData<RemoteMoviesCatalogItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ), pagingSourceFactory = {
                RemoteTopRatedMoviesPaging(
                    api = api
                )
            }).flow
    }

    override fun getUpcomingMovies(): Flow<PagingData<RemoteMoviesCatalogItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ), pagingSourceFactory = {
                RemoteUpcomingMoviesPaging(
                    api = api
                )
            }).flow
    }

    override fun getNowPlayingMovies(): Flow<PagingData<RemoteMoviesCatalogItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ), pagingSourceFactory = {
                RemoteNowPlayingMoviesPaging(
                    api = api
                )
            }).flow
    }

    override fun getSearchedMovies(searchedMovie: String): Flow<PagingData<RemoteMoviesCatalogItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ), pagingSourceFactory = {
                RemoteSearchedMoviesPaging(
                    api = api,
                    searchedMovie = searchedMovie
                )
            }).flow
    }

    override suspend fun getMovieDetails(movieId: String): RemoteMovieDetailsResponse {
        return withContext(Dispatchers.IO) {
            api.getMovieDetails(movieId = movieId, apiKey = BuildConfig.TMDB_KEY)
        }
    }

    override suspend fun getMovieDetailsCast(movieId: String): RemoteMovieDetailsCastItemsResponse {
        return withContext(Dispatchers.IO) {
            api.getMovieDetailsCast(movieId = movieId, apiKey = BuildConfig.TMDB_KEY)
        }
    }
}