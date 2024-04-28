package com.sample.moviedb.data.repository

import com.sample.moviedb.data.db.MovieDatabase
import com.sample.moviedb.data.remote.MovieApiService
import com.sample.moviedb.domain.model.Movie
import com.sample.moviedb.domain.repository.MovieListRepository
import com.sample.moviedb.mappers.toMovie
import com.sample.moviedb.mappers.toMovieEntity
import com.sample.moviedb.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    val movieApiService: MovieApiService,
    val movieDatabase: MovieDatabase
) : MovieListRepository {

    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))

            val localMovieList = movieDatabase.movieDao.getMovieListByCategory(category)
            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            // Local to converting to Movie
            if (shouldLoadLocalMovie) {
                emit(Resource.Success(data = localMovieList.map { movieEntity ->
                    movieEntity.toMovie(category)
                }))

                emit(Resource.Loading(false))
                return@flow
            }

            // If Loading data from Api
            val movieListFromApi = try {
                movieApiService.getMovieList(category, page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }

            val movieEntities = movieListFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity(category)
                }
            }

            // Update database with Entity
            movieDatabase.movieDao.upsertMovieList(movieEntities)

            emit(Resource.Success(data = movieEntities.map {
                it.toMovie(category)
            }))

            emit(Resource.Loading(false))
        }
    }

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {
        return flow {
            emit(Resource.Loading(true))

            val movieEntity = movieDatabase.movieDao.getMovieById(id)

            movieEntity.let {
                emit(Resource.Success(data = movieEntity.toMovie(category = movieEntity.category)))
                return@flow
            }

            emit(Resource.Error(message = "Error, No Such Movie"))
            emit(Resource.Loading(false))
        }
    }

}