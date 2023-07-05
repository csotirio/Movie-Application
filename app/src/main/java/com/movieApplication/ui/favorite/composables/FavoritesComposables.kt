package com.movieApplication.ui.favorite.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.movieApplication.ui.theme.MovieApplicationTheme

@Composable
fun FavoriteScreen() {
    FavoriteContent()
}

@Composable
fun FavoriteContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {

    }
}

@Composable
@Preview(showBackground = true)
fun FavoriteScreenPreview() {
    MovieApplicationTheme {
        FavoriteContent()
    }
}