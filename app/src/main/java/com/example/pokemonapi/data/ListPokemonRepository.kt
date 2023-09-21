package com.example.pokemonapi.data

import com.example.pokemonapi.domain.bean.ListPokemonBean
import com.example.pokemonapi.data.network.ListPokemonService
import javax.inject.Inject

class ListPokemonRepository @Inject constructor(private val api:ListPokemonService){


    suspend fun getListPokemonRepository(): ListPokemonBean?
    {
        val response = api.getPokemonService()
        return response?.toBean()

    }
}