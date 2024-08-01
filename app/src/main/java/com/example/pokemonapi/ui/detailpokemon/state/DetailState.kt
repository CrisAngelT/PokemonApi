package com.example.pokemonapi.ui.detailpokemon.state

import com.example.pokemonapi.domain.bean.DetailPokemonBean
import com.example.pokemonapi.domain.bean.evolution.EvolutionPokemonBean
import com.example.pokemonapi.domain.bean.move.MoveBean
import com.example.pokemonapi.domain.bean.species.SpeciesBean

data class DetailState(
    val isLoading: Boolean = false,
    val data: DetailPokemonBean? = null,
    val evolutionResponse: EvolutionPokemonBean? = null,
    val speciesResponse: SpeciesBean? = null,
    val moveResponse: MoveBean? = null,
    val error: String = ""
)


