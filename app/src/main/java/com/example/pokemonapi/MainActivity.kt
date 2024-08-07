package com.example.pokemonapi

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pokemonapi.commons.navigation.AppNavigation
import com.example.pokemonapi.ui.theme.PokemonApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//

        setContent {
            PokemonApiTheme {
                AppNavigation()
            }


        }
    }

}

