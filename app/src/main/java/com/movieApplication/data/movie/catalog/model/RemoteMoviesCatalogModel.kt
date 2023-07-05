package com.movieApplication.data.movie.catalog.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RemoteMoviesCatalogResponse(
    val results: List<RemoteMoviesCatalogItem>,
    val page: Int,
    @Json(name = "total_pages")
    val totalPages: Int
)

@JsonClass(generateAdapter = true)
data class RemoteMoviesCatalogItem(
    val id: String?,
    val title: String?,
    val overview: String?,
    @Json(name = "poster_path")
    val posterPath: String?
)