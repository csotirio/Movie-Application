package com.movieApplication.ui.favorite.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.movieApplication.ui.allmovies.composables.OnMovieCatalogClicked
import com.movieApplication.ui.favorite.composables.FavoriteScreen
import com.movieApplication.ui.favorite.viewmodel.FavoriteViewModel

const val FavoriteScreenRoute = "favorite_screen"

fun NavGraphBuilder.favoriteScreen(
    onMovieCatalogClicked: OnMovieCatalogClicked
) {
    composable(FavoriteScreenRoute) {

        val viewModel = hiltViewModel<FavoriteViewModel>()
        FavoriteScreen(
            favoriteMovies = viewModel.favoriteMovies,
            onMovieCatalogClicked = onMovieCatalogClicked

        )
    }
}

fun NavController.navigateToFavoriteScreen() {
    this.navigate(FavoriteScreenRoute) {
        this@navigateToFavoriteScreen.currentDestination?.route?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
                inclusive = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}