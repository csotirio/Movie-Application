package com.movieApplication.usecase.movie.catalog

import androidx.paging.PagingData
import com.movieApplication.domain.movie.catalog.MoviesCatalogItem
import com.movieApplication.domain.movie.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<MoviesCatalogItem>> {
        return repository.getUpcomingMovies()
    }
}


