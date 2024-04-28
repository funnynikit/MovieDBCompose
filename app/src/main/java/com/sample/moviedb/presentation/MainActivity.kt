package com.sample.moviedb.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sample.moviedb.Other.OnClickHandler
import com.sample.moviedb.nav.Screens
import com.sample.moviedb.presentation.screens.DetailsScreen
import com.sample.moviedb.presentation.screens.Notification
import com.sample.moviedb.presentation.screens.PopularMoviesScreen
import com.sample.moviedb.presentation.screens.Profile
import com.sample.moviedb.presentation.screens.Search
import com.sample.moviedb.presentation.screens.Settings
import com.sample.moviedb.presentation.screens.UpcomingMoviesScreen
import com.sample.moviedb.presentation.viewmodel.MovieListState
import com.sample.moviedb.presentation.viewmodel.MovieListViewModel
import com.sample.moviedb.ui.theme.MovieDBTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity(), OnClickHandler {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                   // modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    Home(this)
                }
            }
        }
    }

    override fun onClick() {
       Log.d("Check","On Click Called")
        Toast.makeText(this@MainActivity,"Logout Called",Toast.LENGTH_SHORT).show()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(mainActivity: MainActivity) {

    val openDialog = remember {  mutableStateOf(false) }
    val movieListViewModel = hiltViewModel<MovieListViewModel>()
    val movieListState = movieListViewModel.movieListState.collectAsState().value
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current

    if(openDialog.value){
        com.sample.moviedb.Other.AlertDialogComponent(openDialog,"MainActivity",mainActivity)
    }

    val selected = remember {
        mutableStateOf(Icons.Rounded.Movie)
    }

    val sheetState = rememberModalBottomSheetState()

    var showBottomSheet = remember {
        mutableStateOf(false)
    }

    ModalNavigationDrawer(
        drawerState = drawerState, gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .background(com.sample.moviedb.ui.theme.Purple80)
                        .fillMaxWidth()
                        .height(150.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Nikit",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White
                    )
                }
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Popular Movie", color = Color.Black) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Movie,
                            contentDescription = "Home",
                            tint = Color.Black
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }

                        navigationController.navigate(Screens.PopularMovieScreen.screen)
                        {
                            popUpTo(0)
                        }
                    })

                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = Color.LightGray
                )

                // Second Item
                NavigationDrawerItem(
                    label = { Text(text = "Profile", color = Color.Black) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = Color.Black
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }

                        navigationController.navigate(Screens.Profile.screen)
                        {
                            popUpTo(0)
                        }
                    })

                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = Color.LightGray
                )
                // Third Item
                NavigationDrawerItem(
                    label = { Text(text = "Settings", color = Color.Black) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.Black
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }

                        navigationController.navigate(Screens.Settings.screen)
                        {
                            popUpTo(0)
                        }
                    })

                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = Color.LightGray
                )
                // Fourth Item
                NavigationDrawerItem(
                    label = { Text(text = "Logout", color = Color.Black) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Logout",
                            tint = Color.Black
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }

                        // Calling Alert Dialog
                        openDialog.value = true

                    })

                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = Color.LightGray
                )
                // End of Drawer Items
            }
        },
    )
    {
        Scaffold(
            topBar = {
                val coroutineScope = rememberCoroutineScope()

                TopAppBar(
                    title = {
                        Text(
                            text = "Movie App",
                            fontSize = 20.sp
                        )
                    },
                    modifier = Modifier.shadow(2.dp),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Rounded.Menu, contentDescription = "MenuButton")
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        MaterialTheme.colorScheme.inverseOnSurface
                    )
                )
            },
            bottomBar = {
                // Create a Bottom Bar
                CreateBottomBar(selected,showBottomSheet,navigationController)
            }
        ) {
            // NavHost for Screen Routing
            CreateNavHost(movieListState,movieListViewModel,navigationController)
        }
    }

    if (showBottomSheet.value){
        CreateBottomSheet(context,sheetState,showBottomSheet)
    }
}

@Composable
fun BottomSheetItem(icon : ImageVector, title : String, onClick : () -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.clickable { onClick() }
    ) {
        Icon(icon , contentDescription = null, tint = com.sample.moviedb.ui.theme.Purple80)
        Text(text = title,color = com.sample.moviedb.ui.theme.Purple80, fontSize = 18.sp)
    }
}

@Composable
fun CreateBottomBar(
    selected: MutableState<ImageVector>,
    showBottomSheet: MutableState<Boolean>,
    navigationController: NavHostController
) {
    BottomAppBar(containerColor = com.sample.moviedb.ui.theme.Purple80) {
        // First Bottom Nav Item
        IconButton(onClick = {
            selected.value = Icons.Rounded.Movie
            navigationController.navigate(Screens.PopularMovieScreen.screen){
                popUpTo(0)
            }
        }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Rounded.Movie , contentDescription = null, modifier = Modifier.size(26.dp), tint = if (selected.value == Icons.Rounded.Movie) Color.White else Color.Black )
        }

        // Second Bottom Nav Item
        IconButton(onClick = {
            selected.value = Icons.Rounded.Upcoming
            navigationController.navigate(Screens.UpcomingMovieScreen.screen){
                popUpTo(0)
            }
        }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Rounded.Upcoming , contentDescription = null, modifier = Modifier.size(26.dp), tint = if (selected.value == Icons.Rounded.Upcoming) Color.White else Color.Black )
        }

        // Third Bottom Nav Item
        Box(modifier = Modifier
            .weight(1f)
            .padding(16.dp), contentAlignment = Alignment.Center){
            FloatingActionButton(onClick = { showBottomSheet.value = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = com.sample.moviedb.ui.theme.Purple80)
            }
        }

        // Forth Item
        // Second Bottom Nav Item
        IconButton(onClick = {
            selected.value = Icons.Default.Search
            navigationController.navigate(Screens.Search.screen){
                popUpTo(0)
            }
        }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Default.Search , contentDescription = null, modifier = Modifier.size(26.dp), tint = if (selected.value == Icons.Default.Search) Color.White else Color.Black )
        }

        // Fifth Item
        IconButton(onClick = {
            selected.value = Icons.Default.Notifications
            navigationController.navigate(Screens.Notification.screen){
                popUpTo(0)
            }
        }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Default.Notifications , contentDescription = null, modifier = Modifier.size(26.dp), tint = if (selected.value == Icons.Default.Notifications) Color.White else Color.Black )
        }
    }

}

@Composable
fun CreateNavHost(
    movieListState: MovieListState,
    movieListViewModel: MovieListViewModel,
    navigationController: NavHostController
) {
    NavHost(navController = navigationController, startDestination = Screens.PopularMovieScreen.screen){
        composable(Screens.PopularMovieScreen.screen){
            PopularMoviesScreen(movieListState,navigationController,movieListViewModel::onEvent)
        }
        composable(Screens.UpcomingMovieScreen.screen){
            UpcomingMoviesScreen(movieListState,navigationController,movieListViewModel::onEvent)
        }
        composable(Screens.Profile.screen){
            Profile()
        }
        composable(Screens.Settings.screen){
            Settings()
        }
        composable(Screens.Search.screen){
            Search()
        }
        composable(Screens.Notification.screen){
            Notification()
        }
        composable(
            Screens.Detail.screen + "/{movieId}",
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            DetailsScreen()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBottomSheet(
    context: Context,
    sheetState: SheetState,
    showBottomSheet: MutableState<Boolean>
) {
    ModalBottomSheet(onDismissRequest = { showBottomSheet.value = false },sheetState = sheetState, containerColor = com.sample.moviedb.ui.theme.Purple40) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {

            Row(modifier = Modifier
                .padding(start = 20.dp, top = 8.dp, bottom = 8.dp, end = 10.dp)
                .clickable {
                    Toast
                        .makeText(context, "Create a Post", Toast.LENGTH_LONG)
                        .show()
                }, horizontalArrangement = Arrangement.spacedBy(40.dp)) {
                Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "", tint = Color.White)
                Text(text = "Create a Post", color = Color.White)
            }

            Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.White)

            Row(modifier = Modifier
                .padding(start = 20.dp, top = 8.dp, bottom = 8.dp, end = 10.dp)
                .clickable {
                    Toast
                        .makeText(context, "Reels", Toast.LENGTH_LONG)
                        .show()
                }, horizontalArrangement = Arrangement.spacedBy(40.dp)) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "", tint = Color.White)
                Text(text = "Create a Reel", color = Color.White)
            }

            Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.White)
            
           Row(modifier = Modifier
               .padding(start = 20.dp, top = 8.dp, bottom = 8.dp, end = 10.dp)
               .clickable {
                   Toast
                       .makeText(context, "Story", Toast.LENGTH_LONG)
                       .show()
               }, horizontalArrangement = Arrangement.spacedBy(40.dp)) {
               Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = Color.White)
               Text(text = "Add a Story", color = Color.White)
           }
        }
    }
}

