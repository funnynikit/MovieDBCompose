package com.sample.moviedb.presentation

sealed interface MovieListUiEvent {
        data class Paginate(val category: String) : MovieListUiEvent
        object Navigate : MovieListUiEvent
}