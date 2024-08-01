package com.example.pokemonapi.commons.navigation

import kotlinx.serialization.Serializable



@Serializable
object StarAppScreen
@Serializable
object MainAppScreen

@Serializable
data class DetailAppScreen(val idPokemon:String = "",val namePokemon:String = "")


