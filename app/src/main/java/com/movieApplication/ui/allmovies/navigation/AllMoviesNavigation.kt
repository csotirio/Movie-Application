package com.movieApplication.ui.allmovies.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.movieApplication.ui.allmovies.composables.AllMoviesScreen

const val AllMoviesScreenRoute = "all_movies_screen"

fun NavGraphBuilder.allMoviesScreen() {
    composable(AllMoviesScreenRoute) {
        AllMoviesScreen()
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