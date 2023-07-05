package com.movieApplication.ui.allmovies.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.movieApplication.ui.allmovies.model.MoviesTypeSelectionUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllMoviesViewModel @Inject constructor() : ViewModel() {

    val movieTypes = MoviesTypeSelectionUiItem.getMovieTypes()

    private val _selectedType: MutableState<MoviesTypeSelectionUiItem> = mutableStateOf(MoviesTypeSelectionUiItem.PlayingNow)
    val selectedType: State<MoviesTypeSelectionUiItem> = _selectedType

    fun onMovieTypeClicked(type: MoviesTypeSelectionUiItem) {
        _selectedType.value = type
    }
}