package com.movieApplication.ui.lobby.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.movieApplication.ui.lobby.composables.LobbyScreen
import com.movieApplication.ui.lobby.viemodel.LobbyViewModel
import com.movieApplication.util.ext.singleNavigate

const val LobbyScreenRoute = "lobby_screen"

fun NavGraphBuilder.lobbyScreen() {
    composable(LobbyScreenRoute) {

        val viewModel = hiltViewModel<LobbyViewModel>()
        LobbyScreen(
            bottomNavigationUiItems = viewModel.bottomNavigationUiItems
        )
    }
}

fun NavController.navigateToLobbyScreen() {
    singleNavigate(LobbyScreenRoute)
}