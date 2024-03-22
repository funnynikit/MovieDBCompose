package com.sample.moviedb.di

import android.app.Application
import androidx.room.Room
import com.sample.moviedb.data.db.MovieDatabase
import com.sample.moviedb.data.remote.MovieApiService
import com.sample.moviedb.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideMovieApiService() : MovieApiService{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(MovieApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieDatabase(app : Application) : MovieDatabase
    {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            "moviedb.db"
        ).build()
    }

}