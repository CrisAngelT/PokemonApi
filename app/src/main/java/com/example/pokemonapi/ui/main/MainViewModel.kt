package com.example.pokemonapi.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.domain.GetPokemonUseCase
import com.example.pokemonapi.domain.bean.ListPokemonBean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getPokemonUseCase: GetPokemonUseCase) :ViewModel() {
    private val _mutablePokemonResponse = MutableLiveData<ListPokemonBean>()
    val mutablePokemonResponse :LiveData<ListPokemonBean> = _mutablePokemonResponse

    @SuppressLint("SuspiciousIndentation")
    fun onCreate()
    {
        viewModelScope.launch {
            val result = getPokemonUseCase.invoke()
                if (result != null)
                {
                    _mutablePokemonResponse.value = result

                }
        }
    }
}