package com.movieApplication.ui.details.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.movieApplication.ui.details.composables.DetailsScreen
import com.movieApplication.ui.details.composables.OnBackClick
import com.movieApplication.ui.details.viewmodel.MovieDetailsViewModel
import com.movieApplication.util.ext.singleNavigate

const val DetailsScreenRoute = "details_screen/"
const val DETAIL_ARGUMENT_ID = "id"
const val DetailsScreenRouteArg = "$DetailsScreenRoute{$DETAIL_ARGUMENT_ID}"

fun NavGraphBuilder.detailsScreen(
    onBackClick: OnBackClick
) {
    composable(
        DetailsScreenRouteArg,
        arguments = listOf(
            navArgument(DETAIL_ARGUMENT_ID) { type = NavType.StringType }
        )) { backStackEntry ->

        val viewModel = hiltViewModel<MovieDetailsViewModel>()

        LaunchedEffect(key1 = Unit) {
            viewModel.initDetails(backStackEntry.arguments?.getString(DETAIL_ARGUMENT_ID) ?: "")
        }

        DetailsScreen(
            movieDetailsUiState = viewModel.movieDetailsStateUi,
            movieDetailsCastUiState = viewModel.movieDetailsCastUiState,
            onBackClick = onBackClick
        )
    }
}

fun NavController.navigateToDetailsScreen(id: String) {
    singleNavigate(DetailsScreenRoute + id)
}