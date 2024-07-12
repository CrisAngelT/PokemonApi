package com.example.pokemonapi.data.repository

import com.example.pokemonapi.data.network.PokemonApi
import com.example.pokemonapi.domain.bean.DetailPokemonBean
import com.example.pokemonapi.domain.bean.PokemonBean
import com.example.pokemonapi.domain.mapper.toDomain
import com.example.pokemonapi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val api: PokemonApi):PokemonRepository {
    override suspend fun getPokemon(): Flow<PokemonBean> {
        return flow {
            val response = api.getListPokemonApi()
            response.body()?.toDomain()?.let {
                emit(it)
            }

        }
    }

    override suspend fun getInfoPokemon(idPokemon: Int): Flow<DetailPokemonBean> {
       return flow {
            val response = api.listInfoPokemon(idPokemon)
            response.body()?.toDomain()?.let {
                emit(it)
            }
       }
    }
}