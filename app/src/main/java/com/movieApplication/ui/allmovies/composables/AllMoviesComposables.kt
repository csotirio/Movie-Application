package com.movieApplication.ui.allmovies.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow
import com.movieApplication.ui.allmovies.model.MoviesCatalogUiItem
import com.movieApplication.ui.allmovies.model.MoviesTypeSelectionUiItem
import com.movieApplication.ui.general.NormalText
import com.movieApplication.ui.theme.LightBlue
import com.movieApplication.ui.theme.MovieApplicationTheme
import com.movieApplication.ui.theme.NavyBlue
import com.movieApplication.ui.theme.SpacingCustom_12dp
import com.movieApplication.ui.theme.SpacingDefault_16dp
import com.movieApplication.ui.theme.SpacingHalf_8dp
import com.movieApplication.ui.theme.SpacingQuarter_4dp

typealias OnMovieTypeClicked = (MoviesTypeSelectionUiItem) -> Unit
typealias OnMovieCatalogClicked = (String) -> Unit

@Composable
fun AllMoviesScreen(
    movieTypes: List<MoviesTypeSelectionUiItem>,
    selectedType: MoviesTypeSelectionUiItem,
    movies: LazyPagingItems<MoviesCatalogUiItem>,
    onMovieTypeClicked: OnMovieTypeClicked,
    onMovieCatalogClicked: OnMovieCatalogClicked
) {
    AllMoviesContent(
        movieTypes = movieTypes,
        selectedType = selectedType,
        movies = movies,
        onMovieTypeClicked = onMovieTypeClicked,
        onMovieCatalogClicked = onMovieCatalogClicked
    )
}

@Composable
fun AllMoviesContent(
    movieTypes: List<MoviesTypeSelectionUiItem>,
    selectedType: MoviesTypeSelectionUiItem,
    movies: LazyPagingItems<MoviesCatalogUiItem>,
    onMovieTypeClicked: OnMovieTypeClicked,
    onMovieCatalogClicked: OnMovieCatalogClicked
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpacingHalf_8dp),
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
        items(
            items = movies.itemSnapshotList,
        ) { movieCatalogUiItem ->
            if (movieCatalogUiItem != null) {
                MovieItemWithDetails(
                    moviesCatalogUiItem = movieCatalogUiItem,
                    onMovieCatalogClicked = onMovieCatalogClicked
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

    val backgroundColor = if (selectedType == moviesType) {
        NavyBlue
    } else {
        LightBlue
    }

    val backgroundColorState = animateColorAsState(
        targetValue = backgroundColor,
        animationSpec = tween(durationMillis = 300)
    )

    Row(modifier = Modifier
        .clip(RoundedCornerShape(SpacingCustom_12dp))
        .background(backgroundColorState.value)
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
fun MovieItemWithDetails(
    modifier: Modifier = Modifier,
    moviesCatalogUiItem: MoviesCatalogUiItem,
    onMovieCatalogClicked: OnMovieCatalogClicked
) {
    Row(
        modifier = modifier
            .padding(SpacingHalf_8dp)
            .fillMaxWidth(0.9f)
            .aspectRatio(2.5f)
            .clip(RoundedCornerShape(SpacingDefault_16dp))
            .background(Color.White)
            .clickable { onMovieCatalogClicked(moviesCatalogUiItem.id) }, verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = moviesCatalogUiItem.imageUrl,
            contentScale = ContentScale.FillHeight,
            contentDescription = null,
        )
        Column(
            modifier = Modifier.padding(horizontal = SpacingQuarter_4dp)
        ) {
            Text(
                text = moviesCatalogUiItem.titleRes,
                style = androidx.compose.material.MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = SpacingHalf_8dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(SpacingQuarter_4dp))

            Text(
                modifier = Modifier.padding(start = SpacingHalf_8dp),
                text = moviesCatalogUiItem.descriptionRes,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = androidx.compose.material.MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AllMoviesScreenPreview() {
    MovieApplicationTheme {
//        AllMoviesContent(
//            movieTypes = MoviesTypeSelectionUiItem.getMovieTypes(),
//            selectedType = MoviesTypeSelectionUiItem.PlayingNow,
//            movies = ,
//            { _ -> },
//            { _ ->}
//        )
    }
}