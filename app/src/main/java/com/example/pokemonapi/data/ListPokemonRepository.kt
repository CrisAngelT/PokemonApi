package com.example.pokemonapi.data

import com.example.pokemonapi.data.network.ListPokemonService
import javax.inject.Inject

class ListPokemonRepository @Inject constructor(private val api: ListPokemonService) {


    suspend fun getListPokemonRepository(): com.example.pokemonapi.domain.bean.Pokemon? {
        val response = api.getPokemonService()
        return response?.toBean()

    }

    suspend fun getInfoPokemon(id: Int): com.example.pokemonapi.data.model.response.Pokemon? {

        return api.getInfoPokemonService(id)

    }
}