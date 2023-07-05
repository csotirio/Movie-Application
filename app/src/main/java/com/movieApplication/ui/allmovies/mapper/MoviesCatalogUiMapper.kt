package com.movieApplication.ui.allmovies.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.movieApplication.domain.movie.catalog.MoviesCatalogItem
import com.movieApplication.ui.allmovies.model.MoviesCatalogUiItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject


class MoviesCatalogUiMapper @Inject constructor() {

    operator fun invoke(moviesCatalogItem: Flow<PagingData<MoviesCatalogItem>>): Flow<PagingData<MoviesCatalogUiItem>> {
        return moviesCatalogItem.mapNotNull { item ->
            item.map {
                MoviesCatalogUiItem(
                    id = it.id,
                    titleRes = it.title,
                    descriptionRes = it.description,
                    imageUrl = it.imageUrl
                )
            }
        }
    }
}

