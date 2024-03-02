package com.example.pokemonapi.ui.detailpokemon

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.data.model.response.Pokemon
import com.example.pokemonapi.domain.usecase.GetInfoPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailPokemonViewModel  @Inject constructor(private val getInfoPokemonUseCase: GetInfoPokemonUseCase) :
    ViewModel() {
    private val _liveDataInfoPokemon = MutableLiveData<Pokemon?>()
    val liveDataInfoPokemon : LiveData<Pokemon?> = _liveDataInfoPokemon


    @SuppressLint("SuspiciousIndentation")
    fun getInfoPokemon(id:Int)
    {
        viewModelScope.launch {
          val result =  getInfoPokemonUseCase.invoke(id)
            _liveDataInfoPokemon.postValue(result)
        }
    }
}