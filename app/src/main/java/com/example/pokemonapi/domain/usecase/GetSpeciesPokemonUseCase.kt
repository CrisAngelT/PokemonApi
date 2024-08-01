package com.example.pokemonapi.domain.usecase

import com.example.pokemonapi.commons.Resource
import com.example.pokemonapi.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSpeciesPokemonUseCase @Inject constructor(private val repository: PokemonRepository) {
    operator fun invoke(name:String) = flow {
        emit(repository.speciesPokemon(name))
    }.flowOn(Dispatchers.IO)
}