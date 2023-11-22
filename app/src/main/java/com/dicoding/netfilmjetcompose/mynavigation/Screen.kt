package com.dicoding.netfilmjetcompose.mynavigation

sealed class Screen(val route: String) {
    object NetFilmApp: Screen("netFilmApp")
    object About : Screen("about")
    object NetFilmDetail : Screen("netFilmApp/{filmName}") {
        fun createRoute(filmName: String) = "netFilmApp/$filmName"
    }
    object SplashScreenContent : Screen("splashScreenContent")


}