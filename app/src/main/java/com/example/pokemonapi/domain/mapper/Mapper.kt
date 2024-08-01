package com.example.pokemonapi.domain.mapper

import com.example.pokemonapi.data.local.entity.PokemonListEntity
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
            result.toDomain(index + 1)
        }
    )
}
fun ResultPokemonBean.toEntityDomain():PokemonListEntity{
    return PokemonListEntity(
        idPokemon = this.idPokemon,
        name = this.name,
        url = this.url
    )
}
fun PokemonListEntity.toBeanDomain():ResultPokemonBean{
    return ResultPokemonBean(
        idPokemon = this.idPokemon,
        name = this.name,
        url = this.url
    )
}




fun PokemonInfoResponse.toDomain():DetailPokemonBean{

    val listSprites = listOfNotNull(
        this.sprites.other?.showdown?.backDefault,
        this.sprites.other?.showdown?.backFemale,
        this.sprites.other?.showdown?.backShiny,
        this.sprites.other?.showdown?.backShinyFemale,
        this.sprites.other?.showdown?.frontDefault,
        this.sprites.other?.showdown?.frontFemale,
        this.sprites.other?.showdown?.frontShiny,
        this.sprites.other?.showdown?.frontShinyFemale
    )

    return DetailPokemonBean(
        totalCp = this.stats.sumOf { it.baseStat },
        namePokemon = this.name.replaceFirstChar { it.uppercase()},
        height = this.height,
        weight = this.weight,
        typePokemon = this.types.map { it.type.name },
        soundPokemon = this.cries.latest,
        types = this.types.map { it.type.name },
        stats = this.stats.map { it.toDomain() },
        sprites = listSprites
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



