package com.example.pokemonapi.commons.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonapi.ui.main.component.MainPokemonComponent
import com.example.pokemonapi.ui.splash.ScaffoldSplashScreen
import com.example.pokemonapi.data.model.bean.ResultPokemonBean
import com.example.pokemonapi.ui.login.LoginPage


@Composable
fun AppNavigation(listPokemon:List<ResultPokemonBean>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.SplashScreen.route)
    {
        composable(route = AppScreen.SplashScreen.route)
        {
            ScaffoldSplashScreen(navController)
        }
        composable(route = AppScreen.LoginScreen.route)
        {
            LoginPage(navController)
        }
        composable(route = AppScreen.MainScreen.route)
        {
            MainPokemonComponent(listPokemon,navController)
        }
    }
}