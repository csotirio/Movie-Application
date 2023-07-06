package com.movieApplication.data.movie.details.mapper

import com.movieApplication.BuildConfig
import com.movieApplication.data.movie.details.model.RemoteMovieDetailsCastItemsResponse
import com.movieApplication.data.movie.details.model.RemoteMovieDetailsResponse
import com.movieApplication.domain.movie.details.MovieDetailsCastItem
import com.movieApplication.domain.movie.details.MovieDetailsItem
import com.movieApplication.domain.movie.details.MovieDetailsResult
import com.movieApplication.domain.movie.details.MoviesDetailsCastResult
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() {
    operator fun invoke(remoteMovieDetailsResponse: RemoteMovieDetailsResponse?): MovieDetailsResult {
        if (remoteMovieDetailsResponse == null) {
            return MovieDetailsResult.ErrorResult
        }

        val movieDetailsItem =
            MovieDetailsItem(
                id = remoteMovieDetailsResponse.id ?: "",
                title = remoteMovieDetailsResponse.title ?: "",
                description = remoteMovieDetailsResponse.overview ?: "",
                imageUrl = "${BuildConfig.TMDB_MEDIA_HOST_NAME}${remoteMovieDetailsResponse.posterPath}",
                voteAverage = remoteMovieDetailsResponse.voteAvarage ?: "",
                genres = remoteMovieDetailsResponse.genres ?: emptyList()
            )
        return MovieDetailsResult.SuccessResult(movieDetailsItem = movieDetailsItem)
    }
}

class MovieDetailsCastMapper @Inject constructor() {
    operator fun invoke(remoteMovieDetailsCastItemsResponse: RemoteMovieDetailsCastItemsResponse?): MoviesDetailsCastResult {
        if (remoteMovieDetailsCastItemsResponse == null) {
            return MoviesDetailsCastResult.ErrorResult
        }

        val movieDetailsCastItemList = remoteMovieDetailsCastItemsResponse.cast?.mapNotNull {
            MovieDetailsCastItem(
                fullName = it?.fullname ?: "",
                imageUrl = it?.imageUrl ?: ""
            )
        }
        return MoviesDetailsCastResult.SuccessResult(movieDetailsCastItemList = movieDetailsCastItemList ?: emptyList())
    }
}
