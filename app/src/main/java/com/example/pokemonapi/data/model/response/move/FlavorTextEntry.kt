package com.example.pokemonapi.data.model.response.move

import com.example.pokemonapi.data.model.response.species.LanguageResponse
import com.example.pokemonapi.data.model.response.species.VersionResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FlavorTextEntry(
    @Expose @SerializedName("flavor_text") val flavorText: String,
    @Expose @SerializedName("language") val language: LanguageResponse,
    @Expose @SerializedName("versionGroup")val versionGroup: VersionResponse
)