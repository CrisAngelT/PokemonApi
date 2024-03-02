package com.example.pokemonapi.commons.navigation

import com.example.pokemonapi.commons.Constants

sealed class AppScreen(val route:String)
{
    object SplashScreen: AppScreen(route = Constants.SPLASH_SCREEN)
    object LoginScreen: AppScreen(route = Constants.LOGIN_SCREEN)
    object MainScreen: AppScreen(route = Constants.MAIN_SCREEN)
    object DetailScreen: AppScreen(route = Constants.DETAIL_SCREEN)

}