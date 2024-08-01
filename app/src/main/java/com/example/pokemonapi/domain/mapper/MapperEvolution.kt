package com.example.pokemonapi.domain.mapper

import com.example.pokemonapi.commons.util.extractIdFromUrl
import com.example.pokemonapi.data.model.response.evolution.ChainResponse
import com.example.pokemonapi.domain.bean.evolution.ContentEvolutionPokemonBean
import com.example.pokemonapi.domain.bean.evolution.EvolutionPokemonBean


//fun ChainResponse.toEvolutionDomain():EvolutionPokemonBean{
//    val contentPokemonBean = ContentEvolutionPokemonBean()
//    contentPokemonBean.url = this.species.url
//    contentPokemonBean.name = this.species.name
//    contentPokemonBean.url = this.evolvesTo[0].species.url
//    contentPokemonBean.name = this.evolvesTo[0].species.name
//    contentPokemonBean.url = this.evolvesTo[0].evolvesTo[0].species.url
//    contentPokemonBean.name = this.evolvesTo[0].evolvesTo[0].species.name
//    val listContentPokemon = mutableListOf(ContentEvolutionPokemonBean())
//    listContentPokemon.add(contentPokemonBean)
//    return EvolutionPokemonBean(
//        listPokemon = listContentPokemon
//    )
//
//}
fun ChainResponse.toEvolutionDomain(): EvolutionPokemonBean {
    val listContentPokemon = mutableListOf<ContentEvolutionPokemonBean>()

    // Añadir el Pokémon inicial de la cadena
    val initialPokemon = ContentEvolutionPokemonBean().apply {
        idPokemon = extractIdFromUrl(this@toEvolutionDomain.species.url)
        url = this@toEvolutionDomain.species.url
        name = this@toEvolutionDomain.species.name
    }
    listContentPokemon.add(initialPokemon)

    // Recorrer la cadena de evolución
    val currentChain = this.evolvesTo

    currentChain.forEach { data ->
        val firstEvolution = ContentEvolutionPokemonBean().apply {
            idPokemon = extractIdFromUrl(data.species.url)
            url = data.species.url
            name = data.species.name
        }
        listContentPokemon.add(firstEvolution)

        data.evolvesTo.forEach { data2 ->
            val secondEvolution = ContentEvolutionPokemonBean().apply {
                idPokemon = extractIdFromUrl(data2.species.url)
                url = data2.species.url
                name = data2.species.name
            }
            listContentPokemon.add(secondEvolution)
        }
    }

    return EvolutionPokemonBean(
        listPokemon = listContentPokemon
    )
}

