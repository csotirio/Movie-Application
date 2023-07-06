package com.movieApplication.usecase.movie.favorite

import com.movieApplication.domain.movie.repository.MovieRepository
import javax.inject.Inject

class IsFavoriteMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: String): Boolean {
        return repository.isFavoriteMovie(movieId)
    }
}
