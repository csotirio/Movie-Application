package com.movieApplication.domain.movie.details

import com.movieApplication.data.movie.details.model.RemoteMovieDetailsGenresItem


sealed class MovieDetailsResult {
    class SuccessResult(val movieDetailsItem: MovieDetailsItem) : MovieDetailsResult()
    object ErrorResult : MovieDetailsResult()
}

data class MovieDetailsItem(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val voteAverage: String,
    val genres: List<RemoteMovieDetailsGenresItem>
)

sealed class MoviesDetailsCastResult {
    class SuccessResult(val movieDetailsCastItemList: List<MovieDetailsCastItem>) : MoviesDetailsCastResult()
    object ErrorResult : MoviesDetailsCastResult()
}

data class MovieDetailsCastItem(
    val fullName: String,
    val imageUrl: String
)