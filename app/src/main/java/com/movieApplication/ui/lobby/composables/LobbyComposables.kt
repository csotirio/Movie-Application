package com.movieApplication.ui.lobby.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.movieApplication.ui.lobby.model.LobbyBottomNavigationUiItem
import com.movieApplication.ui.lobby.navhost.LobbyNavHost
import com.movieApplication.ui.theme.MovieApplicationTheme

@Composable
fun LobbyScreen(
    bottomNavigationUiItems: List<LobbyBottomNavigationUiItem>
) {
    LobbyContent(bottomNavigationUiItems = bottomNavigationUiItems)
}

@Composable
fun LobbyContent(bottomNavigationUiItems: List<LobbyBottomNavigationUiItem>) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavBar(
                bottomNavigationUiItems = bottomNavigationUiItems,
                onAllMoviesClicked = { },
                onSearchMovieClicked = { },
                onFavoritesMovieClicked = { }
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                LobbyNavHost(
                    navController = navController
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun LobbyScreenPreview() {
    MovieApplicationTheme {
        LobbyContent(
            listOf(
                LobbyBottomNavigationUiItem.AllMoviesTab,
                LobbyBottomNavigationUiItem.SearchMoviesTab,
                LobbyBottomNavigationUiItem.FavoriteMoviesTab
            )
        )
    }
}