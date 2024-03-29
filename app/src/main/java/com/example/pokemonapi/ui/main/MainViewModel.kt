package com.example.pokemonapi.ui.main

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.commons.Resource
import com.example.pokemonapi.commons.gson.BeanMapper
import com.example.pokemonapi.domain.usecase.GetPokemonUseCase
import com.example.pokemonapi.domain.bean.PokemonBean
import com.example.pokemonapi.ui.main.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MainViewModel @Inject constructor(private val getPokemonUseCase: GetPokemonUseCase) :
    ViewModel() {
    private val _pokemonList = mutableStateOf(MainState())
    val pokemonList: State<MainState> get() = _pokemonList
    private val _query: MutableStateFlow<String> = MutableStateFlow("")
    val query: StateFlow<String> get() = _query
    init {
        viewModelScope.launch {
            _query.debounce(100).collectLatest {
                getPokemonList()
            }
        }

    }

    fun setQuery(s: String) {
        _query.value = s
    }

    @SuppressLint("SuspiciousIndentation")
    fun getPokemonList() =
        viewModelScope.launch {
            getPokemonUseCase().onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _pokemonList.value = MainState(isLoading = true)
                    }

                    is Resource.DataError -> {
                        _pokemonList.value =
                            MainState(error(resource.errorMessageOrCode.toString()))

                    }

                    is Resource.Success -> {
                        if (_query.value.isNotBlank()) {
                            _pokemonList.value = MainState(data = resource.data?.resultsListPokemonBean?.filter {
                                    filter -> filter.name.contains(_query.value)
                            })


                        } else {
                            _pokemonList.value =
                                MainState(data = resource.data?.resultsListPokemonBean)
                        }
                    }


                }


            }.launchIn(viewModelScope)

        }
}
