package com.example.pokemonapi.data.repository

import com.example.pokemonapi.data.local.dao.PokemonListDao
import com.example.pokemonapi.data.local.entity.PokemonListEntity
import com.example.pokemonapi.data.remote.PokemonApi
import com.example.pokemonapi.domain.bean.DetailPokemonBean
import com.example.pokemonapi.domain.bean.PokemonBean
import com.example.pokemonapi.domain.bean.ResultPokemonBean
import com.example.pokemonapi.domain.mapper.toBeanDomain
import com.example.pokemonapi.domain.mapper.toDomain
import com.example.pokemonapi.domain.mapper.toEntityDomain
import com.example.pokemonapi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
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


}