package com.example.pokemonapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokemonapi.ui.main.MainViewModel
import com.example.pokemonapi.commons.navigation.AppNavigation
import com.example.pokemonapi.domain.bean.ResultPokemonBean
import com.example.pokemonapi.ui.theme.PokemonApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var listPokemon:List<ResultPokemonBean>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    listPokemon = listOf()
                    val pokemonViewModel: MainViewModel = viewModel()
                    pokemonViewModel.onCreate()
                    val listPokemon by pokemonViewModel.mutablePokemonResponse.observeAsState(initial = listOf())
                    AppNavigation(listPokemon)
                }
            }
        }
    }

}

