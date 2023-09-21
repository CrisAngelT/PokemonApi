package com.example.pokemonapi.data.network

import com.example.pokemonapi.data.model.response.ListPokemonResponse
import com.example.pokemonapi.data.model.response.PokemonResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ListPokemonAPi {

    @GET("pokemon")
    suspend fun getListPokemonApi(): Response<ListPokemonResponse>

}