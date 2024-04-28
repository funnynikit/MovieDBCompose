package com.sample.moviedb.presentation.viewmodel

import com.sample.moviedb.domain.model.Movie

data class DetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)