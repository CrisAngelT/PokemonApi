package com.example.pokemonapi.commons.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pokemonapi.ui.main.screen.MainScreen
import com.example.pokemonapi.ui.detailpokemon.DetailPokemonScreen
import com.example.pokemonapi.ui.map.MapScreen
import com.example.pokemonapi.ui.start.StartScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = StarAppScreen)
    {
        composable<StarAppScreen>{
            StartScreen(navController)
        }
        composable<MainAppScreen>{
            MainScreen(navController)

        }
        composable<DetailAppScreen>{backStackEntry ->
            val idWithNamePokemon = backStackEntry.toRoute<DetailAppScreen>()
            DetailPokemonScreen(navController,idWithNamePokemon)

        }


    }
}