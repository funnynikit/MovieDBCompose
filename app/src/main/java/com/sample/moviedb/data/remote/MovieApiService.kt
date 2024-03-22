package com.sample.moviedb.data.remote

import com.sample.moviedb.data.model.MovieListDto
import com.sample.moviedb.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/{category}")
    suspend fun getMovieList(
        @Path("category") category : String,
        @Query("page") page : Int,
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : MovieListDto

}