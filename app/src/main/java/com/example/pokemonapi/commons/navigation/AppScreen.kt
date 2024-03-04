package com.example.pokemonapi.commons.navigation

import com.example.pokemonapi.commons.constants.Constants

sealed class AppScreen(val route:String)
{
    data object SplashScreen: AppScreen(route = Constants.SPLASH_SCREEN)
    data object LoginScreen: AppScreen(route = Constants.LOGIN_SCREEN)
    data object MainScreen: AppScreen(route = Constants.MAIN_SCREEN)
    data object DetailScreen: AppScreen(route = Constants.DETAIL_SCREEN)

}