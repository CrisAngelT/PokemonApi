package com.example.pokemonapi.domain.usecase

import com.example.pokemonapi.data.ListPokemonRepository
import javax.inject.Inject

class GetInfoPokemonUseCase @Inject constructor(private val repository: ListPokemonRepository) {

    suspend operator fun invoke(id: Int): com.example.pokemonapi.data.model.response.Pokemon? = repository.getInfoPokemon(id)

}