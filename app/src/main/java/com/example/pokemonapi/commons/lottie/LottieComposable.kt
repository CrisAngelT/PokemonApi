package com.example.pokemonapi.commons.lottie

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.pokemonapi.R


@Composable
fun LottieLogoLogin() {
    Column(
        modifier = Modifier.width(350.dp).height(300.dp)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_lottie_pokemon))
        LottieAnimation(composition = composition,  iterations = LottieConstants.IterateForever)
    }

}
@Preview (showBackground = true)
@Composable
fun LottieSplashScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_lottie_splash))
        LottieAnimation(composition = composition,  iterations = LottieConstants.IterateForever)
    }

}