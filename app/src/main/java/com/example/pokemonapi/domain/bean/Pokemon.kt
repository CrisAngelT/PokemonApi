package com.example.pokemonapi.domain.bean


data class Pokemon(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    val results: List<ResultPokemonBean>?
)