package com.example.pokemonapi.domain.bean


data class PokemonBean(
    val resultsListPokemonBean: List<ResultPokemonBean>? = listOf()
)


data class ResultPokemonBean(
    val idPokemon:Int = 0,
    val name: String = "",
    val url: String = ""
)