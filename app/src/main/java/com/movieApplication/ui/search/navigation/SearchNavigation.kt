package com.movieApplication.ui.search.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.movieApplication.ui.allmovies.composables.OnMovieCatalogClicked
import com.movieApplication.ui.search.composables.SearchScreen
import com.movieApplication.ui.search.viewmodel.SearchViewModel

const val SearchScreenRoute = "search_screen"

fun NavGraphBuilder.searchScreen(
    onMovieCatalogClicked: OnMovieCatalogClicked
) {
    composable(SearchScreenRoute) {

        val viewModel = hiltViewModel<SearchViewModel>()

        SearchScreen(
            onSearchClicked = viewModel::onSearchClicked,
            searchedMovies = viewModel.searchedMovies.collectAsLazyPagingItems(),
            onMovieCatalogClicked = onMovieCatalogClicked
        )
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