package com.movieApplication.ui.details.composables

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.movieApplication.R
import com.movieApplication.ui.details.model.MovieDetailsCastUiItem
import com.movieApplication.ui.details.model.MovieDetailsCastUiState
import com.movieApplication.ui.details.model.MovieDetailsUiItem
import com.movieApplication.ui.details.model.MovieDetailsUiState
import com.movieApplication.ui.theme.LightBlue
import com.movieApplication.ui.theme.MovieApplicationTheme
import com.movieApplication.ui.theme.NavyBlue
import com.movieApplication.ui.theme.SpacingCustom_12dp
import com.movieApplication.ui.theme.SpacingDefault_16dp
import com.movieApplication.ui.theme.SpacingHalf_8dp
import com.movieApplication.ui.theme.SpacingQuarter_4dp

typealias OnBackClick = () -> Unit

@Composable
fun DetailsScreen(
    movieDetailsUiState: State<MovieDetailsUiState>,
    movieDetailsCastUiState: State<MovieDetailsCastUiState>,
    onBackClick: OnBackClick
) {
    DetailsContent(
        movieDetailsUiState = movieDetailsUiState,
        movieDetailsCastUiState = movieDetailsCastUiState,
        onBackClick = onBackClick
    )
}

@Composable
fun DetailsContent(
    movieDetailsUiState: State<MovieDetailsUiState>,
    movieDetailsCastUiState: State<MovieDetailsCastUiState>,
    onBackClick: OnBackClick
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Header(titleRes = R.string.movie_details_title, hasNavigateUp = true, onCloseClick = { onBackClick() })

        LazyColumn(
            modifier = Modifier
                .background(NavyBlue)
                .weight(1f)
        ) {

            catalogContent(
                movieDetailsUiState = movieDetailsUiState
            )

            item {
                MovieDetailsCastContent(
                    movieDetailsCastUiState = movieDetailsCastUiState
                )

            }
        }
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    hasNavigateUp: Boolean = false,
    onCloseClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(7f)
            .background(Color.White)
            .padding(SpacingCustom_12dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        if (hasNavigateUp) {
            Image(
                modifier = modifier
                    .fillMaxWidth(0.1f)
                    .aspectRatio(1.5f)
                    .clip(CircleShape)
                    .clickable { onCloseClick() },
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null

            )
        } else {
            Spacer(
                modifier = modifier
                    .fillMaxWidth(0.1f)
                    .aspectRatio(1f)
            )
        }

        Text(
            text = stringResource(titleRes),
            modifier = Modifier
                .weight(1f)
                .wrapContentSize(Alignment.Center),
            textAlign = TextAlign.Center,
            color = NavyBlue,
            fontSize = 22.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.W400
        )

        Spacer(
            modifier = modifier
                .fillMaxWidth(0.1f)
                .aspectRatio(1f)
        )
    }
}

fun LazyListScope.catalogContent(
    movieDetailsUiState: State<MovieDetailsUiState>
) {
    when (val movieDetailsUiStateValue = movieDetailsUiState.value) {
        is MovieDetailsUiState.DefaultUiStateMovies -> {

            detailsDefaultContent(
                movieDetailsUiStateValue.movieDetailsItem, false
            )

        }

        MovieDetailsUiState.ErrorUiStateMovies -> {

        }

        MovieDetailsUiState.LoadingUiStateMovies -> {

        }

        MovieDetailsUiState.EmptyUiStateMovies -> {

        }
    }
}

@Composable
fun MovieDetailsCastContent(
    movieDetailsCastUiState: State<MovieDetailsCastUiState>
) {
    when (val moviesDetailsCastUiState = movieDetailsCastUiState.value) {
        is MovieDetailsCastUiState.DefaultCastUiStateMovies -> {
            MoviesDefaultCastContent(
                false,
                moviesDetailsCastUiState.moviesDetailsCastUiItemList
            )
        }

        MovieDetailsCastUiState.ErrorUiStateMovies -> {

        }

        MovieDetailsCastUiState.EmptyUiStateMovies -> {

        }

        MovieDetailsCastUiState.LoadingUiStateMovies -> {

        }
    }
}

fun LazyListScope.detailsDefaultContent(
    moviesDetailsUiItem: MovieDetailsUiItem,
    boolean: Boolean
) {
    item { DetailsImage(moviesDetailsUiItem.imageUrl, boolean) }
    item { TitleText(title = moviesDetailsUiItem.title, boolean) }
    item { RatingText(overview = "Rating: " + moviesDetailsUiItem.voteAverage + " /10", boolean) }
    item {
        LazyRow(
            modifier = Modifier
                .padding(start = SpacingDefault_16dp)
                .placeholder(
                    visible = boolean,
                    highlight = PlaceholderHighlight.shimmer()
                ),
            contentPadding = PaddingValues(
                horizontal = SpacingHalf_8dp,
                vertical = SpacingHalf_8dp
            ),
            horizontalArrangement = Arrangement.spacedBy(SpacingHalf_8dp)
        ) {
            items(
                items = moviesDetailsUiItem.genres

            ) { genresItem ->
                Genres(item = genresItem.name.toString())
            }
        }
    }
    item { DescriptionText(overview = moviesDetailsUiItem.description, boolean) }
    item { TitleText(title = "Cast", false) }
}


@Composable
fun MoviesDefaultCastContent(
    boolean: Boolean,
    movieDetailsCastUiItemList: List<MovieDetailsCastUiItem>
) {
    LazyRow(
        contentPadding = PaddingValues(
            horizontal = SpacingHalf_8dp, vertical = SpacingHalf_8dp
        ), horizontalArrangement = Arrangement.spacedBy(SpacingHalf_8dp)
    ) {
        items(
            items = movieDetailsCastUiItemList
        ) { movieDetailsCastUiItem ->
            MovieItem(boolean = boolean, movieDetailsCastUiItem = movieDetailsCastUiItem)
        }
    }
}

@Composable
fun MovieItem(
    modifier: Modifier = Modifier, boolean: Boolean, movieDetailsCastUiItem: MovieDetailsCastUiItem
) {
    val currentWidthDp = LocalConfiguration.current.screenWidthDp
    AsyncImage(
        modifier = modifier
            .width((currentWidthDp * 0.3f).dp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .placeholder(
                visible = boolean,
                highlight = PlaceholderHighlight.shimmer(),
            ),
        model = movieDetailsCastUiItem.imageUrl,
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}

@Composable
fun DetailsImage(url: String, boolean: Boolean) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f)
            .background(Color.White)
            .placeholder(
                visible = boolean,
                highlight = PlaceholderHighlight.shimmer(),
            ),
        model = url,
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}

@Composable
private fun TitleText(title: String, boolean: Boolean) {
    Text(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = SpacingDefault_16dp, top = SpacingHalf_8dp)
            .placeholder(
                visible = boolean,
                highlight = PlaceholderHighlight.shimmer(),
            ),
        style = MaterialTheme.typography.titleLarge,
        color = LightBlue,
        text = title,
    )
}

@Composable
private fun DescriptionText(overview: String, boolean: Boolean) {
    Text(
        modifier = Modifier
            .wrapContentSize()
            .padding(SpacingDefault_16dp)
            .placeholder(
                visible = boolean,
                highlight = PlaceholderHighlight.shimmer(),
            ),
        style = MaterialTheme.typography.bodyMedium,
        color = Color.White,
        text = overview
    )
}

@Composable
private fun Genres(item: String) {
    Text(
        modifier = Modifier
            .clip(RoundedCornerShape(SpacingDefault_16dp))
            .background(LightBlue)
            .padding(SpacingQuarter_4dp),
        text = item,
        color = NavyBlue,
        fontSize = 15.sp
    )
}

@Composable
private fun RatingText(overview: String, boolean: Boolean) {
    Text(
        modifier = Modifier
            .wrapContentSize()
            .padding(SpacingDefault_16dp)
            .placeholder(
                visible = boolean,
                highlight = PlaceholderHighlight.shimmer(),
            ),
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Yellow,
        text = overview
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview(showBackground = true)
fun DetailsScreenPreview() {
    MovieApplicationTheme {
        DetailsContent(
            movieDetailsUiState = mutableStateOf(MovieDetailsUiState.LoadingUiStateMovies),
            movieDetailsCastUiState = mutableStateOf(MovieDetailsCastUiState.LoadingUiStateMovies),
            {}
        )
    }
}