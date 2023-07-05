package com.movieApplication.ui.allmovies.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.movieApplication.ui.allmovies.composables.AllMoviesScreen
import com.movieApplication.ui.allmovies.viewmodel.AllMoviesViewModel

const val AllMoviesScreenRoute = "all_movies_screen"

fun NavGraphBuilder.allMoviesScreen() {
    composable(AllMoviesScreenRoute) {

        val viewMode = hiltViewModel<AllMoviesViewModel>()

        AllMoviesScreen(
            movieTypes = viewMode.movieTypes,
            selectedType = viewMode.selectedType.value,
            onMovieTypeClicked = viewMode::onMovieTypeClicked
        )
    }
}

fun NavController.navigateToAllMoviesScreen() {
    this.navigate(AllMoviesScreenRoute) {
        this@navigateToAllMoviesScreen.currentDestination?.route?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
                inclusive = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}