package com.sample.moviedb.data.db

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertMovieList(movieList : List<MovieEntity>)

    @androidx.room.Query("SELECT * FROM MovieEntity WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    @androidx.room.Query("SELECT * FROM MovieEntity WHERE category = :category")
    suspend fun getMovieListByCategory(category: String): List<MovieEntity>

}