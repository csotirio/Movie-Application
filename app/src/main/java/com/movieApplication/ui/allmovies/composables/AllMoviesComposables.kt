package com.movieApplication.ui.allmovies.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.movieApplication.ui.theme.MovieApplicationTheme

@Composable
fun AllMoviesScreen() {
    AllMoviesContent()
}

@Composable
fun AllMoviesContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {

    }
}

@Composable
@Preview(showBackground = true)
fun AllMoviesScreenPreview() {
    MovieApplicationTheme {
        AllMoviesContent()
    }
}