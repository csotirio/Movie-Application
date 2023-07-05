package com.movieApplication.ui.lobby.viemodel

import androidx.lifecycle.ViewModel
import com.movieApplication.ui.lobby.model.LobbyBottomNavigationUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor() : ViewModel() {

    val bottomNavigationUiItems = listOf(
        LobbyBottomNavigationUiItem.AllMoviesTab,
        LobbyBottomNavigationUiItem.SearchMoviesTab,
        LobbyBottomNavigationUiItem.FavoriteMoviesTab
    )


}