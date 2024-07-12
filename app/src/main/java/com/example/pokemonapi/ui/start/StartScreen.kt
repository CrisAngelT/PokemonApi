package com.example.pokemonapi.ui.start


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.navigation.MainAppScreen
import com.example.pokemonapi.commons.screens.CircularProgress
import com.example.pokemonapi.commons.utilcomponent.LogoPokemon
import com.example.pokemonapi.commons.utilcomponent.SetStatusBarColor
import kotlinx.coroutines.delay

@Preview
@Composable
fun StartScreen(navController: NavController? = null) {
    SetStatusBarColor(Color.Black)
    LaunchedEffect(key1 = true)
    {
        delay(2000)
        navController?.popBackStack()
        navController?.navigate(MainAppScreen)
    }
    Scaffold(topBar = {
        LogoPokemon(200.dp, 85.dp)

    }, containerColor = Color.Black) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            Image(
                painter = painterResource(id = R.drawable.walpaper_pokemon),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                contentScale = ContentScale.Crop
            )
        }
    }


}



