package com.movieApplication.ui.details.mapper

import com.movieApplication.BuildConfig
import com.movieApplication.domain.movie.details.MovieDetailsCastItem
import com.movieApplication.domain.movie.details.MovieDetailsItem
import com.movieApplication.ui.details.model.MovieDetailsCastUiItem
import com.movieApplication.ui.details.model.MovieDetailsUiItem
import javax.inject.Inject

class MovieDetailsUiMapper @Inject constructor() {
    operator fun invoke(movieDetailsItem: MovieDetailsItem): MovieDetailsUiItem {
        return MovieDetailsUiItem(
            id = movieDetailsItem.id,
            title = movieDetailsItem.title,
            description = movieDetailsItem.description,
            imageUrl = movieDetailsItem.imageUrl,
            voteAverage = movieDetailsItem.voteAverage,
            genres = movieDetailsItem.genres
        )
    }
}

class MovieDetailsCastUiMapper @Inject constructor() {

    operator fun invoke(movieDetailsCastItemList: List<MovieDetailsCastItem>): List<MovieDetailsCastUiItem> {

        return movieDetailsCastItemList.map {
            MovieDetailsCastUiItem(
                fullName = it.fullName,
                imageUrl = "${BuildConfig.TMDB_MEDIA_HOST_NAME}${it.imageUrl}"
            )
        }
    }
}
