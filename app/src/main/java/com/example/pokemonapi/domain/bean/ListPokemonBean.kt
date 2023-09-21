package com.example.pokemonapi.domain.bean


data class ListPokemonBean(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    val results: List<ResultPokemonBean>?
)