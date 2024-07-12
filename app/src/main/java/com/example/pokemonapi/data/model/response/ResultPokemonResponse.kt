package com.example.pokemonapi.data.model.response

import com.google.gson.annotations.SerializedName

data class ResultPokemonResponse(
    val idPokemon: Int = 0,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)