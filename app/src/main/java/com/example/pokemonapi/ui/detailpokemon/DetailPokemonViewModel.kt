package com.example.pokemonapi.ui.detailpokemon

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.commons.Resource
import com.example.pokemonapi.commons.constants.Constants
import com.example.pokemonapi.commons.preference.Preference
import com.example.pokemonapi.data.model.response.PokemonInfoResponse
import com.example.pokemonapi.domain.usecase.GetInfoPokemonUseCase
import com.example.pokemonapi.ui.detailpokemon.state.DetailState
import com.example.pokemonapi.ui.main.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailPokemonViewModel @Inject constructor(
    private val getInfoPokemonUseCase: GetInfoPokemonUseCase,
    @ApplicationContext context: Context
) :
    ViewModel() {

    private val _detailPokemon = mutableStateOf(DetailState())
    val detailPokemon: State<DetailState> get() = _detailPokemon




    @SuppressLint("SuspiciousIndentation")
    fun getInfoPokemon(id: Int) = viewModelScope.launch {
        getInfoPokemonUseCase(id).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _detailPokemon.value = DetailState(isLoading = true)
                }

                is Resource.Success -> {
                    _detailPokemon.value = DetailState(data = resource.data)
                }

                is Resource.DataError -> {
                    _detailPokemon.value =
                        DetailState(error = resource.errorMessageOrCode.toString())

                }
            }
        }.launchIn(viewModelScope)
    }

}