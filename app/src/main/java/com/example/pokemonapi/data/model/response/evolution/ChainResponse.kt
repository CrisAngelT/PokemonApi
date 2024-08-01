package com.example.pokemonapi.data.model.response.evolution

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ChainResponse(
    @Expose @SerializedName("evolves_to") val evolvesTo: List<EvolvesTo>,
    @Expose @SerializedName("is_baby") val isBaby: Boolean,
    @Expose @SerializedName("species") val species: SpeciesResponse
)