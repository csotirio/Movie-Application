package com.movieApplication.ui.favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.movieApplication.ui.favorite.composables.FavoriteScreen

const val FavoriteScreenRoute = "favorite_screen"

fun NavGraphBuilder.favoriteScreen() {
    composable(FavoriteScreenRoute) {
        FavoriteScreen()
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