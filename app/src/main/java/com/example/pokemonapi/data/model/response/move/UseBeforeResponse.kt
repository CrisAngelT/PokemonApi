package com.example.pokemonapi.data.model.response.move

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UseBeforeResponse(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)