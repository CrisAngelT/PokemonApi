package com.example.pokemonapi.data.model.response.species

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VarietyResponse(
    @Expose @SerializedName("is_default") val isDefault: Boolean,
    @Expose @SerializedName("pokemon") val pokemon: PokemonResponse
)