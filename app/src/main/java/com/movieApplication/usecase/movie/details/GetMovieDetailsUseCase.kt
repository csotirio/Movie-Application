package com.movieApplication.usecase.movie.details

import com.movieApplication.domain.movie.details.MovieDetailsResult
import com.movieApplication.domain.movie.repository.MovieRepository
import timber.log.Timber
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: String): MovieDetailsResult {
        return try {
            repository.getMovieDetails(movieId = movieId)
        } catch (e: Exception) {
            Timber.tag(GetMovieDetailsUseCase::class.simpleName).e(e)
            MovieDetailsResult.ErrorResult
        }
    }
}
