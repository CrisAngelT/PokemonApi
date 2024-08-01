package com.example.pokemonapi.commons.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object LocationPokemonFireStore {

    private val firestoreDB = Firebase.firestore.collection("LocationPokemon")
    fun getAllPokemonCoordinates(): Task<QuerySnapshot> {
        return firestoreDB.get()
    }
}