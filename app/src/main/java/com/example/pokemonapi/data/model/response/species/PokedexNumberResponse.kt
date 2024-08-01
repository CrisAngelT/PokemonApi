package com.example.pokemonapi.data.model.response.species

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokedexNumberResponse(
    @Expose @SerializedName("entryNumber") val entry_number: Int,
    @Expose @SerializedName("pokedex") val pokedex: PokedexResponse
)