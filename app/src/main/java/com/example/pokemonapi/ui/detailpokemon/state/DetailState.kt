package com.example.pokemonapi.ui.detailpokemon.state

import com.example.pokemonapi.data.model.response.PokemonInfoResponse

data class DetailState(
    val isLoading: Boolean = false,
    val data: PokemonInfoResponse? = null,
    val error: String = ""
)
