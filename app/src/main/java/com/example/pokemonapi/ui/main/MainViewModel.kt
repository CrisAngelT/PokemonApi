package com.example.pokemonapi.ui.main

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.commons.Resource
import com.example.pokemonapi.commons.gson.BeanMapper
import com.example.pokemonapi.domain.usecase.GetPokemonUseCase
import com.example.pokemonapi.ui.main.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    val query = _query.asStateFlow()
    private val _isInternetAvailable =  mutableStateOf(false)
    val isInternetAvailable: State<Boolean> get() = _isInternetAvailable
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
    fun validationInternet(){
        _isInternetAvailable.value = true
    }
    fun clearValidationInternet(){
        _isInternetAvailable.value = false
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
                            MainState(error  = resource.errorMessageOrCode.toString())

                    }

                    is Resource.Success -> {
                        resource.data?.collect{ response ->
                            if (_query.value.isNotBlank()) {
                                _pokemonList.value = MainState(data = response.filter { filter -> filter.name.contains(_query.value,ignoreCase = true)
                                })

                            } else {
                                Timber.e("ADD POKEMON -->${BeanMapper.toJson(response)}")
                                _pokemonList.value =
                                    MainState(data = response)
                            }
                        }

                    }


                }


            }.launchIn(viewModelScope)

        }
}
