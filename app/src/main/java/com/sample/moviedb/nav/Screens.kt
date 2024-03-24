package com.sample.moviedb.nav

sealed class Screens(val screen : String) {
    data object PopularMovieScreen : Screens("popular_movie_screen")
    data object UpcomingMovieScreen : Screens("upcoming_movie_screen")
    data object Detail : Screens("detail")
    data object Profile : Screens("profile")
    data object Settings : Screens("settings")
    data object Search : Screens("search")
    data object Notification : Screens("notification")

}