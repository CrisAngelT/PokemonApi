package com.example.pokemonapi.domain.usecase

import com.example.pokemonapi.commons.Resource
import com.example.pokemonapi.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPokemonEvolutionUseCase @Inject constructor(private val repository: PokemonRepository) {
    operator fun invoke(id: Int) = flow {
        emit(Resource.Loading())
        emit(Resource.Success(repository.evolutionPokemon(id)))
    }.catch {
        emit(Resource.DataError(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}