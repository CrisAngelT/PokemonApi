package com.example.pokemonapi.commons.preference

import android.content.Context

object Preference {
    private fun fnPreferencesRead(context: Context, keyPreference: String, mode: Int): String? {
        return context.getSharedPreferences("Actual", mode).getString(keyPreference, "")
    }

    private fun fnPreferencesWrite(
        context: Context,
        keyPreference: String,
        value: String,
        mode: Int
    ) {
        val preferences = context.getSharedPreferences("Actual", mode)
        val editor = preferences.edit()
        editor.putString(keyPreference, value)
        editor.apply()
    }

    fun fnRead(context: Context, keyPreference: String): String? {
        return fnPreferencesRead(context, keyPreference, 4)
    }

    fun fnReadPrivate(context: Context, keyPreference: String): String? {
        return fnPreferencesRead(context, keyPreference, 0)
    }

    fun fnWrite(context: Context, keyPreference: String, value: String) {
        fnPreferencesWrite(context, keyPreference, value, 4)
    }


}