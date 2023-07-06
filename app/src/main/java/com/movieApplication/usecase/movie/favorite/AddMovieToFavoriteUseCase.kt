package com.movieApplication.usecase.movie.favorite

import com.movieApplication.domain.movie.catalog.MoviesCatalogItem
import com.movieApplication.domain.movie.repository.MovieRepository
import javax.inject.Inject

class AddMovieToFavoriteUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movie: MoviesCatalogItem) {
        return repository.addMovieToFavorite(movie)
    }
}
