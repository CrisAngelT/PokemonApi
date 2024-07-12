package com.example.pokemonapi.domain.mapper

import com.example.pokemonapi.data.model.response.PokemonInfoResponse
import com.example.pokemonapi.data.model.response.PokemonResponse
import com.example.pokemonapi.data.model.response.ResultPokemonResponse
import com.example.pokemonapi.data.model.response.Stat
import com.example.pokemonapi.domain.bean.DetailPokemonBean
import com.example.pokemonapi.domain.bean.PokemonBean
import com.example.pokemonapi.domain.bean.ResultPokemonBean
import com.example.pokemonapi.domain.bean.StatBean

fun ResultPokemonResponse.toDomain(id: Int): ResultPokemonBean {
    return ResultPokemonBean(
        idPokemon = id,
        name = this.name,
        url = this.url
    )
}


fun PokemonResponse.toDomain(): PokemonBean {
    return PokemonBean(
        resultsListPokemonBean = this.results.mapIndexed { index, result ->
            result.toDomain(index + 1) // Incrementamos el ID empezando en 1
        }
    )
}

fun PokemonInfoResponse.toDomain():DetailPokemonBean{
    return DetailPokemonBean(
        totalCp = this.stats.sumOf { it.baseStat },
        namePokemon = this.name.replaceFirstChar { it.uppercase()},
        height = this.height,
        weight = this.weight,
        typePokemon = this.types.map { it.type.name },
        soundPokemon = this.cries.latest,
        types = this.types.map { it.type.name },
        stats = this.stats.map { it.toDomain() }
    )
}

fun Stat.toDomain():StatBean
{
    return StatBean(
        baseStat = this.baseStat,
        effort = this.effort,
        stat = this.stat
    )
}



