package com.example.pokemonapi.ui.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.lottie.LottieSplashScreen
import com.example.pokemonapi.commons.navigation.AppScreen
import com.example.pokemonapi.commons.utilcomponent.SetStatusBarColor
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SplashScreen(navController: NavController)
{
    Scaffold(
        content = { ContentSplashScreen(navController = navController ) }
    )
}


@Preview
@Composable
fun ContentSplashScreen(navController: NavController?=null) {
    SetStatusBarColor(color = colorResource(id = R.color.color_fondo_splash))
    LaunchedEffect(key1 = true)
    {
        delay(1000)
        navController?.popBackStack()
        navController?.navigate(AppScreen.LoginScreen.route)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.color_fondo_splash))) {
       LottieSplashScreen()
    }
}