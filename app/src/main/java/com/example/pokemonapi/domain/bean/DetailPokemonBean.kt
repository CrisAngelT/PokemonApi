package com.example.pokemonapi.domain.bean

import com.example.pokemonapi.data.model.response.StatInfo


data class DetailPokemonBean(
    val totalCp:Int,
    val namePokemon:String,
    val height:Int,
    val weight:Int,
    val typePokemon:List<String>,
    val soundPokemon:String,
    val types:List<String>,
    val stats:List<StatBean>
)

data class StatBean(
    val baseStat: Int,
    val effort: Int,
    val stat: StatInfo
)