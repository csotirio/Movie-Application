package com.movieApplication.ui.details.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieApplication.domain.movie.details.MovieDetailsResult
import com.movieApplication.domain.movie.details.MoviesDetailsCastResult
import com.movieApplication.ui.details.mapper.MovieDetailsCastUiMapper
import com.movieApplication.ui.details.mapper.MovieDetailsUiMapper
import com.movieApplication.ui.details.model.MovieDetailsCastUiState
import com.movieApplication.ui.details.model.MovieDetailsUiState
import com.movieApplication.usecase.movie.details.GetMovieDetailsCastUseCase
import com.movieApplication.usecase.movie.details.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieDetailsCastUseCase: GetMovieDetailsCastUseCase,
    private val movieDetailsUiMapper: MovieDetailsUiMapper,
    private val movieDetailsCastUiMapper: MovieDetailsCastUiMapper
) : ViewModel() {

    private val _movieDetailsStateUi: MutableState<MovieDetailsUiState> = mutableStateOf(MovieDetailsUiState.LoadingUiStateMovies)
    val movieDetailsStateUi: State<MovieDetailsUiState> = _movieDetailsStateUi

    private val _movieDetailsCastStateUi: MutableState<MovieDetailsCastUiState> = mutableStateOf(MovieDetailsCastUiState.LoadingUiStateMovies)
    val movieDetailsCastUiState: State<MovieDetailsCastUiState> = _movieDetailsCastStateUi

    fun initDetails(movieId: String) {
        getMovieDetails(movieId = movieId)
        getMovieCast(movieId = movieId)
    }


    private fun getMovieDetails(movieId: String) {
        viewModelScope.launch {
            val movieDetailsResult = getMovieDetailsUseCase(movieId = movieId)
            val state = when (movieDetailsResult) {
                is MovieDetailsResult.SuccessResult -> {
                    val movieDetailsUiItem = movieDetailsUiMapper(movieDetailsItem = movieDetailsResult.movieDetailsItem)
                    MovieDetailsUiState.DefaultUiStateMovies(movieDetailsUiItem)
                }

                MovieDetailsResult.ErrorResult -> {
                    MovieDetailsUiState.ErrorUiStateMovies
                }
            }
            _movieDetailsStateUi.value = state
        }
    }

    private fun getMovieCast(movieId: String) {
        viewModelScope.launch {
            val movieDetailsCastResult = getMovieDetailsCastUseCase(movieId = movieId)
            delay(2000)
            val state = when (movieDetailsCastResult) {
                is MoviesDetailsCastResult.SuccessResult -> {
                    val movieDetailsCastUiItemList =
                        movieDetailsCastUiMapper(movieDetailsCastItemList = movieDetailsCastResult.movieDetailsCastItemList)
                    MovieDetailsCastUiState.DefaultCastUiStateMovies(movieDetailsCastUiItemList)
                }

                MoviesDetailsCastResult.ErrorResult -> {
                    MovieDetailsCastUiState.ErrorUiStateMovies
                }
            }
            _movieDetailsCastStateUi.value = state
        }
    }
}