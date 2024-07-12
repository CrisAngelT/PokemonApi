package com.example.pokemonapi.commons.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Slider
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.colors.ColorLazyGridItem
import com.example.pokemonapi.commons.colors.ColorPokemonTypeMap


@Preview
@Composable
fun TextView(
    word: String = stringResource(id = R.string.app_name),
    fontSize: Int = 14,
    color: Int = R.color.black,
    isBold: Boolean = false,
    modifierAll: Modifier = Modifier,
) {
    Text(
        text = word,
        modifier = modifierAll,
        fontSize = fontSize.sp,
        textAlign = TextAlign.Center,
        fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
        color = colorResource(id = color)
    )

}

@Composable
fun SpacerViewWithHeight(spacer: Int = 10) {
    Spacer(modifier = Modifier.height(spacer.dp))
}

@Preview
@Composable
fun SliderMinimalExample(typePokemon: String = "", baseStat: Float = 1f) {
    var sliderPosition by rememberSaveable { mutableFloatStateOf(baseStat) }
    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            enabled = false,
            colors = androidx.compose.material.SliderDefaults.colors(
                activeTickColor = Color.Black,
                disabledActiveTrackColor = ColorPokemonTypeMap[typePokemon] ?: Color.Yellow,
                thumbColor = Color.Transparent,
                disabledThumbColor = Color.Transparent,
                disabledInactiveTrackColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
            ),


            )

    }
}
@Preview
@Composable
fun CircularProgress(idPokemon:Int = 7){
    val strokeWidth = 5.dp
    Column(
        modifier = Modifier.fillMaxWidth().background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            color = ColorLazyGridItem[idPokemon % ColorLazyGridItem.size],
            strokeWidth = strokeWidth
        )
    }
}