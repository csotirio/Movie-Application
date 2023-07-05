package com.movieApplication.ui.lobby.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.movieApplication.R

sealed class LobbyBottomNavigationUiItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val isSelected: MutableState<Boolean>
) {
    object AllMoviesTab : LobbyBottomNavigationUiItem(
        R.string.all_movies_bottom_navigation_title,
        R.drawable.ic_all_movies,
        mutableStateOf(true)
    )

    object SearchMoviesTab : LobbyBottomNavigationUiItem(
        R.string.search_movies_bottom_navigation_title,
        R.drawable.ic_search,
        mutableStateOf(false)
    )

    object FavoriteMoviesTab : LobbyBottomNavigationUiItem(
        R.string.favorite_movies_bottom_navigation_title,
        R.drawable.ic_favorites,
        mutableStateOf(false)
    )
}