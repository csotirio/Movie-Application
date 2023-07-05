package com.movieApplication.ui.allmovies.model

import androidx.annotation.StringRes
import com.movieApplication.R


sealed class MoviesTypeSelectionUiItem(
    @StringRes val title: Int
) {
    object PlayingNow : MoviesTypeSelectionUiItem(
        title = R.string.playing_now_label
    )

    object Popular : MoviesTypeSelectionUiItem(
        title = R.string.popular_label
    )

    object TopRated : MoviesTypeSelectionUiItem(
        title = R.string.top_rated_label
    )

    object Upcoming : MoviesTypeSelectionUiItem(
        title = R.string.upcoming_label
    )

    companion object {
        fun getMovieTypes(): List<MoviesTypeSelectionUiItem> {
            return listOf(PlayingNow, Popular, TopRated, Upcoming)
        }
    }
}