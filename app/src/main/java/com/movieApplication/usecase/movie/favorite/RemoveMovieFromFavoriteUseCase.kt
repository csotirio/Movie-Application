package com.movieApplication.usecase.movie.favorite

import com.movieApplication.domain.movie.repository.MovieRepository
import javax.inject.Inject

class RemoveMovieFromFavoriteUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: String) {
        return repository.removeFromFavoriteMovies(movieId)
    }
}
