package com.example.pokemonapi.commons.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonapi.domain.bean.Pokemon
import com.example.pokemonapi.ui.detailpokemon.DetailPokemonViewModel
import com.example.pokemonapi.ui.main.component.MainPokemonComponent
import com.example.pokemonapi.ui.splash.ScaffoldSplashScreen
import com.example.pokemonapi.ui.detailpokemon.detailPokemonComponent
import com.example.pokemonapi.ui.login.LoginPage
import com.example.pokemonapi.ui.main.MainViewModel


@Composable
fun AppNavigation(mainViewModel: MainViewModel, detailViewModel: DetailPokemonViewModel) {
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
            MainPokemonComponent(mainViewModel,navController)
        }
        composable(route = AppScreen.DetailScreen.route)
        {
            detailPokemonComponent(navController,detailViewModel)
        }
    }
}