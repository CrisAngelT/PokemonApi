package com.example.pokemonapi.ui.main.state

import com.example.pokemonapi.domain.bean.ResultPokemonBean

data class MainState(
    val isLoading: Boolean = false,
    val data: List<ResultPokemonBean>? = null,
    val error: String = ""
)

