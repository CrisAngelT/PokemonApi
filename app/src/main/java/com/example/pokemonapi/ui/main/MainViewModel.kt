package com.example.pokemonapi.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.commons.Resource
import com.example.pokemonapi.domain.usecase.GetPokemonUseCase
import com.example.pokemonapi.domain.bean.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getPokemonUseCase: GetPokemonUseCase) :ViewModel() {
    private val _mutablePokemonResponse = MutableLiveData<Resource<Pokemon?>>()
    val mutablePokemonResponse :LiveData<Resource<Pokemon?>> = _mutablePokemonResponse

    @SuppressLint("SuspiciousIndentation")
    fun onCreate()
    {
        viewModelScope.launch {
            _mutablePokemonResponse.value = Resource.Loading()
            val result = getPokemonUseCase.invoke()
                if (result != null)
                {
                    _mutablePokemonResponse.value = Resource.Success(result)

                }
        }
    }
}