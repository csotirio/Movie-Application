package com.movieApplication.ui.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.movieApplication.ui.search.composables.SearchScreen

const val SearchScreenRoute = "search_screen"

fun NavGraphBuilder.searchScreen() {
    composable(SearchScreenRoute) {
        SearchScreen()
    }
}

fun NavController.navigateToSearchScreen() {
    this.navigate(SearchScreenRoute) {
        this@navigateToSearchScreen.currentDestination?.route?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
                inclusive = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}