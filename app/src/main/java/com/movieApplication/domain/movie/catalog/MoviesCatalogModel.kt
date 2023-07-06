package com.movieApplication.domain.movie.catalog

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_movies_table")
data class MoviesCatalogItem(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val imageUrl: String
)
