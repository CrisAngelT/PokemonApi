package com.example.pokemonapi.data.model.response.species

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PalParkEncounterResponse(
    @Expose @SerializedName("area") val area: AreaResponse,
    @Expose @SerializedName("base_score") val baseScore: Int,
    @Expose @SerializedName("rate") val rate: Int
)