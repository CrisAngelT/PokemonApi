package com.example.pokemonapi.data.model.response.evolution

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonEvolutionResponse(
    @Expose @SerializedName("chain") val chain: ChainResponse,
    @Expose @SerializedName("id") val id: Int
)