package com.movieApplication.usecase.movie.details

import com.movieApplication.domain.movie.details.MoviesDetailsCastResult
import com.movieApplication.domain.movie.repository.MovieRepository
import timber.log.Timber
import javax.inject.Inject

class GetMovieDetailsCastUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: String): MoviesDetailsCastResult {
        return try {
            repository.getMovieDetailsCast(movieId = movieId)
        } catch (e: Exception) {
            Timber.tag(GetMovieDetailsCastUseCase::class.simpleName).e(e)
            MoviesDetailsCastResult.ErrorResult
        }
    }
}
