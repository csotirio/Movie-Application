package com.movieApplication.ui.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.movieApplication.ui.splash.composables.NavigateToLobby
import com.movieApplication.ui.splash.composables.SplashScreen

const val SplashScreenRoute = "splash_screen"

fun NavGraphBuilder.splashScreen(
    navigateToLobby: NavigateToLobby
) {
    composable(SplashScreenRoute) {
        SplashScreen(navigateToLobby = navigateToLobby)
    }
}