package com.example.pokemon.data

import com.example.pokemonapi.data.model.bean.ListPokemonBean
import com.example.pokemonapi.data.network.ListPokemonService

class ListPokemonRepository {

    private val api = ListPokemonService()

    suspend fun getListPokemonRepository(): ListPokemonBean?
    {
        val response = api.getPokemonService()
        return response?.toBean()

    }
}