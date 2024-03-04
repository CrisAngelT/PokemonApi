package com.example.pokemonapi.data.network

import com.example.pokemonapi.data.model.response.PokemonResponse
import com.example.pokemonapi.data.model.response.PokemonInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon/?offset=0&limit=100")
    suspend fun getListPokemonApi(): Response<PokemonResponse>

    @GET("pokemon/{id}")
    suspend fun listInfoPokemon(@Path("id") id: Int): Response<PokemonInfoResponse>
}