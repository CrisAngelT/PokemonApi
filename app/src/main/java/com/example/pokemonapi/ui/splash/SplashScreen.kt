package com.example.pokemonapi.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.lottie.LottieSplashScreen
import com.example.pokemonapi.commons.navigation.MainAppScreen
import kotlinx.coroutines.delay


@Preview
@Composable
fun SplashScreen(navController: NavController?=null)
{
    LaunchedEffect(key1 = true)
    {
        delay(2000)
        navController?.popBackStack()
        navController?.navigate(MainAppScreen)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.color_fondo_splash))) {
        LottieSplashScreen()
    }
}
