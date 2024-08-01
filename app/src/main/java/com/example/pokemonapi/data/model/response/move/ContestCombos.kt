package com.example.pokemonapi.data.model.response.move

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContestCombos(
    @Expose @SerializedName("normal") val normal: NormalResponse?=null
)