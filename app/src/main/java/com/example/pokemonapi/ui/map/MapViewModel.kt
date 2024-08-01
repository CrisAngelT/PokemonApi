package com.example.pokemonapi.ui.map

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pokemonapi.commons.firebase.LocationPokemonFireStore.getAllPokemonCoordinates
import com.example.pokemonapi.commons.firebase.PokemonLocation
import com.example.pokemonapi.commons.gson.BeanMapper
import timber.log.Timber

class MapViewModel:ViewModel() {
    private val _mapState = mutableStateOf(MapState())
    val mapState: State<MapState> get() = _mapState

     fun getCoordinateFirebase(){
         _mapState.value = MapState(isLoading = true)
         try {
             getAllPokemonCoordinates().addOnSuccessListener { querySnapshot ->
                 val objectLocationsPokemon =  querySnapshot.documents.map { document ->
                     document.toObject(PokemonLocation::class.java)
                 }
                 _mapState.value = MapState(listPokemon = objectLocationsPokemon,isLoading = false )
                 Timber.e("LOCATION POKEMON --> ${BeanMapper.toJson(objectLocationsPokemon)}")
             }
         }
         catch (e:Exception){
             _mapState.value = MapState(isLoading = false)
             e.printStackTrace()
         }

    }

}