package com.movieApplication.ui.allmovies.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.flowlayout.FlowRow
import com.movieApplication.ui.allmovies.model.MoviesTypeSelectionUiItem
import com.movieApplication.ui.general.NormalText
import com.movieApplication.ui.theme.MovieApplicationTheme
import com.movieApplication.ui.theme.SpacingCustom_12dp
import com.movieApplication.ui.theme.SpacingHalf_8dp

typealias OnMovieTypeClicked = (MoviesTypeSelectionUiItem) -> Unit

@Composable
fun AllMoviesScreen(
    movieTypes: List<MoviesTypeSelectionUiItem>,
    selectedType: MoviesTypeSelectionUiItem,
    onMovieTypeClicked: OnMovieTypeClicked
) {
    AllMoviesContent(
        movieTypes = movieTypes,
        selectedType = selectedType,
        onMovieTypeClicked = onMovieTypeClicked
    )
}

@Composable
fun AllMoviesContent(
    movieTypes: List<MoviesTypeSelectionUiItem>,
    selectedType: MoviesTypeSelectionUiItem,
    onMovieTypeClicked: OnMovieTypeClicked
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = SpacingHalf_8dp),
            mainAxisSpacing = SpacingHalf_8dp,
            crossAxisSpacing = SpacingHalf_8dp
        ) {
            movieTypes.forEach { item ->
                MovieTypeSelectionBtn(
                    moviesType = item,
                    selectedType = selectedType,
                    onMovieTypeClicked = onMovieTypeClicked
                )
            }
        }
    }
}

@Composable
fun MovieTypeSelectionBtn(
    moviesType: MoviesTypeSelectionUiItem,
    selectedType: MoviesTypeSelectionUiItem,
    onMovieTypeClicked: OnMovieTypeClicked
) {
    val textColor = if (selectedType == moviesType) {
        Color.White
    } else {
        Color.Black
    }

    val textColorState = animateColorAsState(
        targetValue = textColor,
        animationSpec = tween(durationMillis = 300)
    )

    Row(modifier = Modifier
        .clip(RoundedCornerShape(SpacingCustom_12dp))
        .background(Color.Red)
        .clickable {
            onMovieTypeClicked(moviesType)
        }
        .padding(SpacingHalf_8dp)
    ) {
        NormalText(
            text = stringResource(id = moviesType.title),
            color = textColorState.value
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AllMoviesScreenPreview() {
    MovieApplicationTheme {
        AllMoviesContent(
            movieTypes = MoviesTypeSelectionUiItem.getMovieTypes(),
            selectedType = MoviesTypeSelectionUiItem.PlayingNow,
            { _ -> }
        )
    }
}