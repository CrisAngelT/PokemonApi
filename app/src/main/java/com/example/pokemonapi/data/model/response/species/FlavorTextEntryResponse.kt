package com.example.pokemonapi.data.model.response.species

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FlavorTextEntryResponse(
    @Expose @SerializedName("flavor_text")  val flavorText: String,
    @Expose @SerializedName("language") val language: LanguageResponse,
    @Expose @SerializedName("version")val version: VersionResponse
)