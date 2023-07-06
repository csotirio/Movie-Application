package com.movieApplication.framework.movie.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.movieApplication.BuildConfig
import com.movieApplication.data.movie.catalog.model.RemoteMoviesCatalogItem
import com.movieApplication.framework.movie.api.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class RemoteSearchedMoviesPaging @Inject constructor(
    val api: MovieApi,
    private val searchedMovie: String
) : PagingSource<Int, RemoteMoviesCatalogItem>() {

    companion object {
        private const val INITIAL_KEY = 1
    }

    override fun getRefreshKey(state: PagingState<Int, RemoteMoviesCatalogItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RemoteMoviesCatalogItem> {

        return try {
            withContext(Dispatchers.IO) {
                val result = api.getSearchedMovies(apiKey = BuildConfig.TMDB_KEY, page = params.key ?: INITIAL_KEY, movie = searchedMovie)
                val page = result.page
                val prevKey = if (page == INITIAL_KEY) null else page - 1
                val nextKey = if (result.totalPages == page) null else page + 1

                LoadResult.Page(data = result.results, prevKey = prevKey, nextKey = nextKey)
            }
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }
}