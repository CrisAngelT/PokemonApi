package com.example.pokemonapi.data.repository

import com.example.pokemonapi.data.model.response.PokemonInfoResponse
import com.example.pokemonapi.data.network.PokemonService
import com.example.pokemonapi.domain.bean.PokemonBean
import javax.inject.Inject

//class PokemonRepository @Inject constructor(private val api: PokemonService) {
//    suspend fun getListPokemonRepository(): PokemonBean? {
//        val response = api.getPokemonService()
//        return response?.toBean()
//
//    }
//
//    suspend fun getInfoPokemon(id: Int): PokemonInfoResponse? {
//
//        return api.getInfoPokemonService(id)
//
//    }
//}