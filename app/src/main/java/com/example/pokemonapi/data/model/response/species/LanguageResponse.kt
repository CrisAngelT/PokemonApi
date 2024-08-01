package com.example.pokemonapi.data.model.response.species

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LanguageResponse(
    @Expose @SerializedName("name")  val name: String,
    @Expose @SerializedName("url") val url: String
)