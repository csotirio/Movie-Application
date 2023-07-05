package com.movieApplication.util.ext

import androidx.navigation.NavController

/*
 * prevent open 2 times
 */
fun NavController.singleNavigate(
    destinationRoute: String,
    singleTop: Boolean = true
) { //TODO check for recomposition in case of single top, to remove line 38
    if (currentDestination?.route != destinationRoute) {
        navigate(destinationRoute) {
            launchSingleTop = singleTop
        }
    }
}