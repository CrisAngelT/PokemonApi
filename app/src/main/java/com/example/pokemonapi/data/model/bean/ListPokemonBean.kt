package com.example.pokemonapi.data.model.bean


data class ListPokemonBean(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    val results: List<ResultPokemonBean>?
)