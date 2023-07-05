package com.movieApplication.ui.lobby.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movieApplication.R
import com.movieApplication.ui.lobby.model.LobbyBottomNavigationUiItem
import com.movieApplication.ui.theme.MovieApplicationTheme
import com.movieApplication.ui.theme.SpacingHalf_8dp
import com.movieApplication.ui.theme.primaryDarkColor

typealias OnAllMoviesClicked = () -> Unit
typealias OnSearchMovieClicked = () -> Unit
typealias OnFavoritesMovieClicked = () -> Unit

@Composable
fun NavBar(
    bottomNavigationUiItems: List<LobbyBottomNavigationUiItem>,
    onAllMoviesClicked: OnAllMoviesClicked,
    onSearchMovieClicked: OnSearchMovieClicked,
    onFavoritesMovieClicked: OnFavoritesMovieClicked
) {
    BottomNavigation(
        modifier = Modifier.fillMaxHeight(0.1f)
    ) {
        bottomNavigationUiItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Image(
                        modifier = Modifier
                            .height(50.dp)
                            .aspectRatio(1f),
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = R.string.empty),
                        contentScale = ContentScale.Fit,

                        )
                },
                selectedContentColor = primaryDarkColor,
                unselectedContentColor = Color.Black.copy(0.4f),
                selected = item.isSelected.value,
                onClick = {
                    if (!item.isSelected.value) {
                        bottomNavigationUiItems.forEach { it.isSelected.value = false }
                        item.onClick(
                            onAllMoviesClicked = onAllMoviesClicked,
                            onSearchMovieClicked = onSearchMovieClicked,
                            onFavoritesMovieClicked = onFavoritesMovieClicked
                        )
                        item.isSelected.value = true
                    }
                },
                label = {
                    Text(
                        modifier = Modifier.padding(top = SpacingHalf_8dp),
                        color = if (item.isSelected.value) {
                            primaryDarkColor
                        } else Color.Black.copy(0.4f),
                        text = stringResource(id = item.title)
                    )
                }
            )
        }
    }
}

fun LobbyBottomNavigationUiItem.onClick(
    onAllMoviesClicked: OnAllMoviesClicked,
    onSearchMovieClicked: OnSearchMovieClicked,
    onFavoritesMovieClicked: OnFavoritesMovieClicked
) {
    when (this) {
        LobbyBottomNavigationUiItem.AllMoviesTab -> {
            onAllMoviesClicked()
        }

        LobbyBottomNavigationUiItem.SearchMoviesTab -> {
            onSearchMovieClicked()
        }

        LobbyBottomNavigationUiItem.FavoriteMoviesTab -> {
            onFavoritesMovieClicked()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NavBarPreview() {
    MovieApplicationTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            NavBar(
                listOf(
                    LobbyBottomNavigationUiItem.AllMoviesTab,
                    LobbyBottomNavigationUiItem.SearchMoviesTab,
                    LobbyBottomNavigationUiItem.FavoriteMoviesTab
                ),
                {}, {}, {})
        }
    }
}