package com.movieApplication.ui.dashboard.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.movieApplication.ui.splash.navigation.SplashScreenRoute
import com.movieApplication.ui.splash.navigation.splashScreen


@Composable
fun DashboardNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SplashScreenRoute
    ) {
        splashScreen(navigateToAllMoviesScreen = {})
    }
}