package com.sample.moviedb.presentation.NavController

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sample.moviedb.presentation.screens.DetailsScreen
import com.sample.moviedb.presentation.screens.HomeScreen

@Composable
fun AppNavHost(navController: NavHostController)
{
    NavHost(
        navController = navController,
        startDestination = Screen.Home.rout
    ) {
        composable(Screen.Home.rout) {
            HomeScreen(navController)
        }

        composable(
            Screen.Details.rout + "/{movieId}",
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            DetailsScreen()
        }
    }
}
