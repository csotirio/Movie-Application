package com.movieApplication.data.movie.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.movieApplication.domain.movie.catalog.MoviesCatalogItem

@Database(
    entities = [MoviesCatalogItem::class],
    version = 1
)
abstract class FavoriteMoviesDatabase : RoomDatabase() {
    abstract val favoriteMoviesDao: FavoriteMoviesDao
}
