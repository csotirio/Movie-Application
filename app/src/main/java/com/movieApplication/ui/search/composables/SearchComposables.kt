package com.movieApplication.ui.search.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.movieApplication.ui.theme.MovieApplicationTheme

@Composable
fun SearchScreen() {
    SearchContent()
}

@Composable
fun SearchContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {

    }
}

@Composable
@Preview(showBackground = true)
fun SearchScreenPreview() {
    MovieApplicationTheme {
        SearchContent()
    }
}