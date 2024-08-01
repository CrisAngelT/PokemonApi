package com.example.pokemonapi.commons.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.pokemonapi.R

@Composable
fun AlertDialogPokemon(
    isShow:Boolean, subTitle: String = "",
    onConfirmClick: () -> Unit
) {
    if (isShow) {
        Column(
            modifier = Modifier.fillMaxSize().background(Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AlertDialog(
                onDismissRequest = {
                    onConfirmClick()
                },
                title = { Text(text = stringResource(id = R.string.app_name)) },
                text = { Text(text = subTitle) },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.color_linear),
                        ),
                        onClick = {
                            onConfirmClick()
                        }
                    ) {
                        Text(
                            text = "Aceptar",
                            color = White
                        )
                    }
                }
            )
        }



    }
}