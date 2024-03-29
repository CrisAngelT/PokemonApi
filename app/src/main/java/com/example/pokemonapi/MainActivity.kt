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
import com.example.pokemonapi.ui.detailpokemon.DetailPokemonViewModel
import com.example.pokemonapi.ui.theme.PokemonApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonApiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val pokemonViewModel: MainViewModel = viewModel()
                    val detailViewModel:DetailPokemonViewModel = viewModel()
                    AppNavigation(pokemonViewModel,detailViewModel)
                }
            }


        }
    }

}

