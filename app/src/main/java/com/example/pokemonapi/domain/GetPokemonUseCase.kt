package com.example.pokemonapi.domain

import com.example.pokemonapi.data.ListPokemonRepository
import com.example.pokemonapi.domain.bean.ListPokemonBean
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val repository: ListPokemonRepository) {

    suspend operator fun invoke(): ListPokemonBean? = repository.getListPokemonRepository()

}