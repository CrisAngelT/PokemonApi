package com.example.pokemonapi.domain.usecase

import com.example.pokemonapi.commons.Resource
import com.example.pokemonapi.domain.bean.DetailPokemonBean
import com.example.pokemonapi.domain.repository.PokemonRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetInfoPokemonUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: PokemonRepository
    private lateinit var getInfoPokemonUseCase: GetInfoPokemonUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getInfoPokemonUseCase = GetInfoPokemonUseCase(repository)
    }

    @Test
    fun `when you ask for an id in letters`() = runBlocking {
        val responseData =
            DetailPokemonBean(15, "Bulbasaur", 1, 1, listOf(), "", listOf(), listOf(), listOf())
        //Given
        coEvery {
            repository.getInfoPokemon(1)
        } returns flowOf(responseData)

        //When
        getInfoPokemonUseCase(1).collect{
        resource ->
            resource.data?.collect{ response ->
                assert(response == responseData)

            }
        }


    }

    @Test
    fun `when you ask for an non-existent id`() = runBlocking {
        //Given
        coEvery {
            repository.getInfoPokemon(-1)
        } returns flowOf()
        //When
        val flow = getInfoPokemonUseCase(-1)
        //Then
        flow.collect { resource ->

            when(resource){
                is Resource.DataError -> {
                    assert(resource.errorMessageOrCode == "HTTP 404 Response.error()")
                }
                is Resource.Loading -> {}
                is Resource.Success -> {}
            }
        }
    }
}