package com.movieApplication.usecase.movie.search

import androidx.paging.PagingData
import com.movieApplication.domain.movie.catalog.MoviesCatalogItem
import com.movieApplication.domain.movie.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchedMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(searchedMovie: String): Flow<PagingData<MoviesCatalogItem>> {
        return repository.getSearchedMovies(searchedMovie)
    }
}
