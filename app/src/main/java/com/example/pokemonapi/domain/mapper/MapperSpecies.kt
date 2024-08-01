package com.example.pokemonapi.domain.mapper

import com.example.pokemonapi.commons.util.extractIdFromUrl
import com.example.pokemonapi.data.model.response.species.SpeciesResponse
import com.example.pokemonapi.domain.bean.species.SpeciesBean

fun SpeciesResponse.toDomain():SpeciesBean{
    return SpeciesBean(
        idPokemonWithEvolution =  extractIdFromUrl(this.evolutionChain.url)
    )
}