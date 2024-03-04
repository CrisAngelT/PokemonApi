package com.example.pokemonapi.domain.usecase

import com.example.pokemonapi.commons.Resource
import com.example.pokemonapi.data.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val repository: PokemonRepository) {


    operator fun invoke() = flow {
        emit(Resource.Loading())
        emit(Resource.Success(repository.getListPokemonRepository()))
    }.catch {
        emit(Resource.DataError(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}