package com.movieApplication.ui.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.movieApplication.ui.allmovies.mapper.MoviesCatalogUiMapper
import com.movieApplication.ui.allmovies.model.MoviesCatalogUiItem
import com.movieApplication.usecase.movie.search.GetSearchedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val moviesCatalogUiMapper: MoviesCatalogUiMapper,
    private val getSearchedMoviesUseCase: GetSearchedMoviesUseCase
) : ViewModel() {

    private val _searchedMovies: MutableStateFlow<PagingData<MoviesCatalogUiItem>> = MutableStateFlow(PagingData.empty())
    val searchedMovies = _searchedMovies.asStateFlow()

    fun onSearchClicked(searchedName: String) {
        viewModelScope.launch {
            val response = getSearchedMoviesUseCase(searchedName)
            _searchedMovies.emitAll(moviesCatalogUiMapper(moviesCatalogItem = response))
        }
    }
}