package com.sample.moviedb.domain.repository

import com.sample.moviedb.utils.Resource
import com.sample.moviedb.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {

    suspend fun getMovieList(
         forceFetchFromRemote : Boolean,
         category : String,
         page : Int
    ) : Flow<Resource<List<Movie>>>

    suspend fun getMovie(id : Int) : Flow<Resource<Movie>>
}