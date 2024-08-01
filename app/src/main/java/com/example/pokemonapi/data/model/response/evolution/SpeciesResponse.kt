package com.example.pokemonapi.data.model.response.evolution

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SpeciesResponse(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)