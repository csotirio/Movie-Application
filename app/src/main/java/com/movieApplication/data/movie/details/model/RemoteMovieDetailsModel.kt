package com.movieApplication.data.movie.details.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteMovieDetailsResponse(
    val id: String?,
    val title: String?,
    val overview: String?,
    @Json(name = "backdrop_path")
    val posterPath: String?,
    @Json(name = "vote_average")
    val voteAvarage: String?,
    val genres: List<RemoteMovieDetailsGenresItem>
)


@JsonClass(generateAdapter = true)
data class RemoteMovieDetailsGenresItem(
    val name: String?
)

@JsonClass(generateAdapter = true)
data class RemoteMovieDetailsCastItemsResponse(
    val cast: List<RemoteMovieDetailsCastItem?>?
)

@JsonClass(generateAdapter = true)
data class RemoteMovieDetailsCastItem(
    @Json(name = "original_name")
    val fullname: String?,
    @Json(name = "profile_path")
    val imageUrl: String?
)