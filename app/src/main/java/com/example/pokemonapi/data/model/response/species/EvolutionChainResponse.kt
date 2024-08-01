package com.example.pokemonapi.data.model.response.species

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EvolutionChainResponse(
    @Expose @SerializedName("url") val url: String
)