package com.example.pokemonapi.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.data.model.bean.ResultPokemonBean
import com.example.pokemonapi.domain.GetPokemonUseCase
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    val _mutablePokemonResponse = MutableLiveData<List<ResultPokemonBean>>()
    val mutablePokemonResponse :LiveData<List<ResultPokemonBean>> = _mutablePokemonResponse
    var getPokemonCaseUse = GetPokemonUseCase()

    @SuppressLint("SuspiciousIndentation")
    fun onCreate()
    {
        viewModelScope.launch {
            val result = getPokemonCaseUse()
                if (result != null)
                {
                    _mutablePokemonResponse.value = result.results

                }
        }
    }
}