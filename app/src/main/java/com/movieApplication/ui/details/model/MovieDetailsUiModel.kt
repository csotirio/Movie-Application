package com.movieApplication.ui.details.model

import com.movieApplication.data.movie.details.model.RemoteMovieDetailsGenresItem


sealed class MovieDetailsCastUiState {
    class DefaultCastUiStateMovies(val moviesDetailsCastUiItemList: List<MovieDetailsCastUiItem>) : MovieDetailsCastUiState()
    object ErrorUiStateMovies : MovieDetailsCastUiState()
    object LoadingUiStateMovies : MovieDetailsCastUiState()
    object EmptyUiStateMovies : MovieDetailsCastUiState()
}

sealed class MovieDetailsUiState {
    class DefaultUiStateMovies(val movieDetailsItem: MovieDetailsUiItem) : MovieDetailsUiState()
    object ErrorUiStateMovies : MovieDetailsUiState()
    object LoadingUiStateMovies : MovieDetailsUiState()
    object EmptyUiStateMovies : MovieDetailsUiState()
}

data class MovieDetailsUiItem(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val voteAverage: String,
    val genres: List<RemoteMovieDetailsGenresItem>
)

data class MovieDetailsCastUiItem(
    val fullName: String,
    val imageUrl: String
)
