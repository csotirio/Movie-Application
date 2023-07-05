package com.movieApplication.data.movie.catalog.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.movieApplication.BuildConfig
import com.movieApplication.data.movie.catalog.model.RemoteMoviesCatalogItem
import com.movieApplication.domain.movie.catalog.MoviesCatalogItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class MoviesCatalogMapper @Inject constructor() {
    operator fun invoke(remoteMoviesCatalogResponse: Flow<PagingData<RemoteMoviesCatalogItem>>): Flow<PagingData<MoviesCatalogItem>> {

        return remoteMoviesCatalogResponse.mapNotNull { item ->
            item.map {
                MoviesCatalogItem(
                    id = it.id ?: "",
                    title = it.title ?: "",
                    description = it.overview ?: "",
                    imageUrl = "${BuildConfig.TMDB_MEDIA_HOST_NAME}${it.posterPath}"
                )
            }
        }
    }
}