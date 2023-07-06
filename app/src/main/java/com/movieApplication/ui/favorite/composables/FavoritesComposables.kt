package com.movieApplication.ui.favorite.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.movieApplication.ui.allmovies.composables.MovieItemWithDetails
import com.movieApplication.ui.allmovies.composables.OnMovieCatalogClicked
import com.movieApplication.ui.allmovies.model.MoviesCatalogUiItem
import com.movieApplication.ui.theme.MovieApplicationTheme

@Composable
fun FavoriteScreen(
    favoriteMovies: List<MoviesCatalogUiItem>,
    onMovieCatalogClicked: OnMovieCatalogClicked
) {
    FavoriteContent(
        favoriteMovies = favoriteMovies,
        onMovieCatalogClicked = onMovieCatalogClicked
    )
}

@Composable
fun FavoriteContent(
    favoriteMovies: List<MoviesCatalogUiItem>,
    onMovieCatalogClicked: OnMovieCatalogClicked
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = favoriteMovies,
        ) { movieCatalogUiItem ->
            MovieItemWithDetails(
                moviesCatalogUiItem = movieCatalogUiItem,
                onMovieCatalogClicked = onMovieCatalogClicked
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FavoriteScreenPreview() {
    MovieApplicationTheme {
        FavoriteContent(emptyList(), { _ -> })
    }
}