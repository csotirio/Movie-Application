package com.movieApplication.ui.favorite.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieApplication.ui.allmovies.mapper.MoviesCatalogUiMapper
import com.movieApplication.ui.allmovies.model.MoviesCatalogUiItem
import com.movieApplication.usecase.movie.favorite.GetAllFavoriteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val moviesCatalogUiMapper: MoviesCatalogUiMapper,
    private val getAllFavoriteMoviesUseCase: GetAllFavoriteMoviesUseCase
) : ViewModel() {

    private var _favoriteMovies = mutableStateListOf<MoviesCatalogUiItem>()
    val favoriteMovies: List<MoviesCatalogUiItem> = _favoriteMovies

    init {
        viewModelScope.launch {
            getAllFavoriteMoviesUseCase().collect { favorites ->
                _favoriteMovies.addAll(moviesCatalogUiMapper(favorites))
            }
        }
    }
}