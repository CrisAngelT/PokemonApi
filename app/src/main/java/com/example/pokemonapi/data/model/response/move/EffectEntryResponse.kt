package com.example.pokemonapi.data.model.response.move

import com.example.pokemonapi.data.model.response.species.LanguageResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EffectEntryResponse(
    @Expose @SerializedName("effect") val effect: String,
    @Expose @SerializedName("language")val language: LanguageResponse,
    @Expose @SerializedName("short_effect")val shortEffect: String
)