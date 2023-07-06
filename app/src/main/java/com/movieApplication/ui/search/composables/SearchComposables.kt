package com.movieApplication.ui.search.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.movieApplication.R
import com.movieApplication.ui.allmovies.composables.MovieItemWithDetails
import com.movieApplication.ui.allmovies.composables.OnMovieCatalogClicked
import com.movieApplication.ui.allmovies.model.MoviesCatalogUiItem
import com.movieApplication.ui.theme.NavyBlue
import com.movieApplication.ui.theme.SpacingCustom_12dp

typealias OnSearchClicked = (String) -> Unit

@Composable
fun SearchScreen(
    onSearchClicked: OnSearchClicked,
    searchedMovies: LazyPagingItems<MoviesCatalogUiItem>,
    onMovieCatalogClicked: OnMovieCatalogClicked
) {
    SearchContent(
        onSearchClicked = onSearchClicked,
        searchedMovies = searchedMovies,
        onMovieCatalogClicked = onMovieCatalogClicked
    )
}

@Composable
fun SearchContent(
    onSearchClicked: OnSearchClicked,
    searchedMovies: LazyPagingItems<MoviesCatalogUiItem>,
    onMovieCatalogClicked: OnMovieCatalogClicked
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.search_for_movie_title),
            textAlign = TextAlign.Center,
            color = NavyBlue,
            fontSize = 22.sp,
            lineHeight = 24.sp
        )

        val searchText = remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SpacingCustom_12dp),
            value = searchText.value,
            onValueChange = { newText -> searchText.value = newText },
            shape = RoundedCornerShape(SpacingCustom_12dp)
        )
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { onSearchClicked(searchText.value) }) {
            Text(
                text = stringResource(R.string.search_for_movie_btn),
                textAlign = TextAlign.Center,
                color = NavyBlue,
                fontSize = 22.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.W400
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = searchedMovies.itemSnapshotList,
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
}

@Composable
@Preview(showBackground = true)
fun SearchScreenPreview() {
//    MovieApplicationTheme {
//        SearchContent(
//            {_ ->}
//        )
//    }
}