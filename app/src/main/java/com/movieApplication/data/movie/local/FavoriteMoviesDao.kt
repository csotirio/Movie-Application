package com.movieApplication.data.movie.local

import androidx.room.*
import com.movieApplication.domain.movie.catalog.MoviesCatalogItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavoriteMovies(movie: MoviesCatalogItem)

    @Query("DELETE FROM favorite_movies_table WHERE id =:movieId")
    suspend fun removeFromFavoriteMovies(movieId: String)

    @Query("SELECT EXISTS (SELECT 1 FROM favorite_movies_table WHERE id = :movieId)")
    suspend fun isFavoriteMovie(movieId: String): Int

    @Query("SELECT * FROM favorite_movies_table")
    fun getAllFavoriteMovies(): Flow<List<MoviesCatalogItem>>
}
