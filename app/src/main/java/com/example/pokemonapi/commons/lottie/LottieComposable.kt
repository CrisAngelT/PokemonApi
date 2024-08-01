package com.example.pokemonapi.commons.lottie

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.*
import com.example.pokemonapi.R

@Preview(showBackground = true)
@Composable
fun LottieComposable(modifier: Modifier = Modifier,jsonAnimation:Int = R.raw.animation_lottie_pokemon) {
    Column(
        modifier = modifier
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(jsonAnimation))
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
    }

}

@Preview(showBackground = true)
@Composable
fun LottieSplashScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_lottie_pokemon))
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.fillMaxSize()
        )
    }

}