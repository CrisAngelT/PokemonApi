package com.example.pokemonapi.domain.usecase

import com.example.pokemonapi.data.ListPokemonRepository
import com.example.pokemonapi.domain.bean.Pokemon
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val repository: ListPokemonRepository) {

    suspend operator fun invoke(): Pokemon? = repository.getListPokemonRepository()

}