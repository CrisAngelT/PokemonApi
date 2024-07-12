package com.example.pokemonapi.data.model.response

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("results") val results: List<ResultPokemonResponse> = listOf()
)