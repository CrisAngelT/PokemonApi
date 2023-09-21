package com.example.pokemon.commons

import android.content.Context
import android.widget.Toast

class Extensions {
}

fun toastUtil(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
