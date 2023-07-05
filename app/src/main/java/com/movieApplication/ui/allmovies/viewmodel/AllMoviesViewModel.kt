package com.movieApplication.ui.allmovies.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.movieApplication.ui.allmovies.mapper.MoviesCatalogUiMapper
import com.movieApplication.ui.allmovies.model.MoviesCatalogUiItem
import com.movieApplication.ui.allmovies.model.MoviesTypeSelectionUiItem
import com.movieApplication.usecase.movie.catalog.GetNowPlayingMoviesUseCase
import com.movieApplication.usecase.movie.catalog.GetPopularMoviesUseCase
import com.movieApplication.usecase.movie.catalog.GetTopRatedMoviesUseCase
import com.movieApplication.usecase.movie.catalog.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllMoviesViewModel @Inject constructor(
    private val moviesCatalogUiMapper: MoviesCatalogUiMapper,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val topRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val upcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : ViewModel() {

    val movieTypes = MoviesTypeSelectionUiItem.getMovieTypes()

    private val _selectedType: MutableState<MoviesTypeSelectionUiItem> = mutableStateOf(MoviesTypeSelectionUiItem.PlayingNow)
    val selectedType: State<MoviesTypeSelectionUiItem> = _selectedType

    private val _movies: MutableStateFlow<PagingData<MoviesCatalogUiItem>> = MutableStateFlow(PagingData.empty())
    val movies = _movies.asStateFlow()

    fun onMovieTypeClicked(type: MoviesTypeSelectionUiItem) {
        _selectedType.value = type
        viewModelScope.launch {
            val response = when (type) {
                MoviesTypeSelectionUiItem.PlayingNow -> {
                    getNowPlayingMoviesUseCase()
                }

                MoviesTypeSelectionUiItem.Popular -> {
                    getPopularMoviesUseCase()
                }

                MoviesTypeSelectionUiItem.TopRated -> {
                    topRatedMoviesUseCase()
                }

                MoviesTypeSelectionUiItem.Upcoming -> {
                    upcomingMoviesUseCase()
                }
            }
            _movies.emitAll(moviesCatalogUiMapper(moviesCatalogItem = response))
        }
    }
}