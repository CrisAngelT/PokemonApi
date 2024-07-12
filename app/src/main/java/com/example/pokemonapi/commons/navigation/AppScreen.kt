package com.example.pokemonapi.commons.navigation

import kotlinx.serialization.Serializable


@Serializable
object SplashAppScreen

@Serializable
object StarAppScreen
@Serializable
object MainAppScreen

@Serializable
data class DetailAppScreen(val data:String = "")


