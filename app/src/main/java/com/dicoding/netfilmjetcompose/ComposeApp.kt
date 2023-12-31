package com.dicoding.netfilmjetcompose


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.netfilmjetcompose.mynavigation.NavigationItem
import com.dicoding.netfilmjetcompose.mynavigation.Screen
import com.dicoding.netfilmjetcompose.ui.about.About
import com.dicoding.netfilmjetcompose.ui.details.NetFilmDetailScreen
import com.dicoding.netfilmjetcompose.ui.film.NetFilmsApp
import com.dicoding.netfilmjetcompose.ui.theme.NetFilmTheme


@Composable
fun ComposeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.NetFilmDetail.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.NetFilmApp.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.NetFilmApp.route) {
                NetFilmsApp(
                    navigateToDetail = { filmName ->
                        navController.navigate(Screen.NetFilmDetail.createRoute(filmName))
                    }
                )
            }
            composable(Screen.About.route) {
                About()
            }
            composable(
                route = Screen.NetFilmDetail.route,
                arguments = listOf(
                    navArgument("filmName") { type = NavType.StringType }
                )
            ) {
                val name = it.arguments?.getString("filmName") ?: ""
                NetFilmDetailScreen(
                    filmName = name,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}


@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(R.string.menu_home),
            icon = Icons.Default.Home,
            screen = Screen.NetFilmApp
        ),
        NavigationItem(
            title = stringResource(R.string.menu_profile),
            icon = Icons.Default.AccountCircle,
            screen = Screen.About
        )
    )
    BottomNavigation(
        modifier = modifier
    ) {
        navigationItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ComposeAppPreview() {
    NetFilmTheme {
        ComposeApp()
    }
}
