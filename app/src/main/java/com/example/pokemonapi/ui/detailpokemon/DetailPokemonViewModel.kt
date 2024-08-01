package com.example.pokemonapi.ui.detailpokemon

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.commons.Resource
import com.example.pokemonapi.commons.gson.BeanMapper
import com.example.pokemonapi.domain.usecase.GetInfoPokemonUseCase
import com.example.pokemonapi.domain.usecase.GetMovePokemonUseCase
import com.example.pokemonapi.domain.usecase.GetPokemonEvolutionUseCase
import com.example.pokemonapi.domain.usecase.GetSpeciesPokemonUseCase
import com.example.pokemonapi.ui.detailpokemon.state.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class DetailPokemonViewModel @Inject constructor(
    private val getInfoPokemonUseCase: GetInfoPokemonUseCase,
    private val getPokemonEvolutionUseCase: GetPokemonEvolutionUseCase,
    private val getSpeciesPokemonUseCase: GetSpeciesPokemonUseCase,
    private val getMovePokemonUseCase: GetMovePokemonUseCase
): ViewModel() {
    var state by mutableStateOf(DetailState())
        private set
    private val _indexFeaturesPokemon = mutableIntStateOf(0)
     val indexFeaturesPokemon:State<Int> get() = _indexFeaturesPokemon

    fun indexIncrementalFeaturePokemon() = viewModelScope.launch {
        _indexFeaturesPokemon.intValue += 1
    }
    fun indexDecrementalFeaturePokemon() = viewModelScope.launch {
        _indexFeaturesPokemon.intValue -= 1
    }

    @SuppressLint("SuspiciousIndentation")
    fun getInfoPokemon(id: Int) = viewModelScope.launch {
        getInfoPokemonUseCase(id).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }

                is Resource.Success -> {
                    resource.data?.collect { response ->
                        state = state.copy(isLoading = false, data = response)

                    }
                }

                is Resource.DataError -> {
                    state = state.copy(isLoading = false,error = resource.errorMessageOrCode.toString())


                }
            }
        }.launchIn(viewModelScope)
    }

    @SuppressLint("SuspiciousIndentation")
    fun getEvolutionPokemonById(id: Int) = viewModelScope.launch {
        getPokemonEvolutionUseCase(id).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }

                is Resource.Success -> {
                    resource.data?.collect { response ->
                        state = state.copy(isLoading = false,evolutionResponse = response)
                    }
                }

                is Resource.DataError -> {
                    state = state.copy(isLoading = false,error = resource.errorMessageOrCode.toString())

                }
            }
        }.launchIn(viewModelScope)
    }

    @SuppressLint("SuspiciousIndentation")
    fun getSpeciesPokemonByName(name: String) =
        viewModelScope.launch {
        getSpeciesPokemonUseCase(name).onEach { resource ->
            resource.collect{
                state = state.copy(isLoading = false,speciesResponse = it)
            }
        }.launchIn(viewModelScope)
    }

    @SuppressLint("SuspiciousIndentation")
    fun getMovePokemonById(id: Int) = viewModelScope.launch {
        getMovePokemonUseCase(id).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }

                is Resource.Success -> {
                    resource.data?.collect { response ->
                        Timber.e("getMovePokemonById: ${BeanMapper.toJson(response)}")
                        state = state.copy(isLoading = false, moveResponse = response)
                    }
                }

                is Resource.DataError -> {
                    state = state.copy(isLoading = false,error = resource.errorMessageOrCode.toString())

                }
            }
        }.launchIn(viewModelScope)
    }

}