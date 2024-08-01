package com.example.pokemonapi.data.repository

import com.example.pokemonapi.data.local.dao.PokemonListDao
import com.example.pokemonapi.data.model.response.move.MoveResponse
import com.example.pokemonapi.data.remote.PokemonApi
import com.example.pokemonapi.domain.bean.DetailPokemonBean
import com.example.pokemonapi.domain.bean.ResultPokemonBean
import com.example.pokemonapi.domain.bean.evolution.EvolutionPokemonBean
import com.example.pokemonapi.domain.bean.move.MoveBean
import com.example.pokemonapi.domain.bean.species.SpeciesBean
import com.example.pokemonapi.domain.mapper.toBeanDomain
import com.example.pokemonapi.domain.mapper.toDomain
import com.example.pokemonapi.domain.mapper.toEntityDomain
import com.example.pokemonapi.domain.mapper.toEvolutionDomain
import com.example.pokemonapi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val api: PokemonApi,
    private val pokemonListDao: PokemonListDao,
):PokemonRepository {
    override suspend fun getPokemon(): Flow<List<ResultPokemonBean>> {
        return flow {
            val getListPokemon = pokemonListDao.getPokemon()
            if (getListPokemon.isEmpty()){
                api.getListPokemonApi().body()?.toDomain()?.resultsListPokemonBean?.let { listPokemon ->
                    pokemonListDao.insertListPokemon(listPokemon.map { it.toEntityDomain() })
                    emit(listPokemon)
                }
            }else{
                emit(getListPokemon.map { it.toBeanDomain() })
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

    override suspend fun evolutionPokemon(idPokemon: Int): Flow<EvolutionPokemonBean> {
        return flow {
            val response = api.evolutionChain(idPokemon)
            response.body()?.let {
                emit(it.chain.toEvolutionDomain())
            }
        }
    }

    override suspend fun speciesPokemon(name: String): Flow<SpeciesBean> {
        return flow {
            val response = api.species(name)
            response.body()?.let {
                emit(it.toDomain())
            }
        }
    }

    override suspend fun movePokemon(idPokemon: Int): Flow<MoveBean> {
        return flow {
            val response = api.movePokemon(idPokemon)
            response.body()?.let {
                emit(it.toDomain())
            }
        }
    }


}