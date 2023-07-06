package com.movieApplication.ui.lobby.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.movieApplication.ui.allmovies.navigation.AllMoviesScreenRoute
import com.movieApplication.ui.allmovies.navigation.allMoviesScreen
import com.movieApplication.ui.details.navigation.detailsScreen
import com.movieApplication.ui.details.navigation.navigateToDetailsScreen
import com.movieApplication.ui.favorite.navigation.favoriteScreen
import com.movieApplication.ui.search.navigation.searchScreen


@Composable
fun LobbyNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AllMoviesScreenRoute
    ) {
        allMoviesScreen(
            onMovieCatalogClicked = { id -> navController.navigateToDetailsScreen(id = id) }
        )
        searchScreen(
            onMovieCatalogClicked = { id -> navController.navigateToDetailsScreen(id = id) }
        )
        favoriteScreen()
        detailsScreen(onBackClick = { navController.navigateUp() })
    }
}