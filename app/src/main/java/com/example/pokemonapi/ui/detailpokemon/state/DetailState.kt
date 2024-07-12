package com.example.pokemonapi.ui.detailpokemon.state

import com.example.pokemonapi.domain.bean.DetailPokemonBean

data class DetailState(
    val isLoading: Boolean = false,
    val data: DetailPokemonBean? = null,
    val error: String = ""
)
