package com.example.pokemonapi.commons.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonapi.ui.detailpokemon.DetailPokemonViewModel
import com.example.pokemonapi.ui.main.screen.MainScreen
import com.example.pokemonapi.ui.splash.SplashScreen
import com.example.pokemonapi.ui.detailpokemon.DetailPokemonScreen
import com.example.pokemonapi.ui.start.StartScreen
import com.example.pokemonapi.ui.main.MainViewModel


@Composable
fun AppNavigation(mainViewModel: MainViewModel, detailViewModel: DetailPokemonViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.SplashScreen.route)
    {
        composable(route = AppScreen.SplashScreen.route)
        {
            SplashScreen(navController)
        }
        composable(route = AppScreen.LoginScreen.route)
        {
            StartScreen(navController)
        }
        composable(route = AppScreen.MainScreen.route)
        {
            MainScreen(mainViewModel,navController)
        }
        composable(route = AppScreen.DetailScreen.route)
        {
            DetailPokemonScreen(navController,detailViewModel)
        }
    }
}