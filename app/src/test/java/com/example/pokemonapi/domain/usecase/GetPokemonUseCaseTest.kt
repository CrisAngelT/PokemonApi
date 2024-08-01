package com.example.pokemonapi.domain.usecase

import com.example.pokemonapi.domain.bean.ResultPokemonBean
import com.example.pokemonapi.domain.repository.PokemonRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPokemonUseCaseTest{
    @RelaxedMockK
    private lateinit var repository: PokemonRepository
    private lateinit var getPokemonUseCase: GetPokemonUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPokemonUseCase = GetPokemonUseCase(repository)
    }

    @Test
    fun `when is not empty then return pokemon list`() = runBlocking {
        val listPokemon = listOf(ResultPokemonBean())
        coEvery {
            repository.getPokemon()
        } returns flowOf(listPokemon)
        //when
        getPokemonUseCase().collect{
            resource ->
            resource.data?.collect{
                assert( it == listPokemon)

            }
        }
    }
}