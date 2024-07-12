package com.example.pokemonapi.commons.utilcomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pokemonapi.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController



@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}
@Composable
fun LogoPokemon(width: Dp,height:Dp) {
    Box(modifier = Modifier.fillMaxWidth())
    {
        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 30.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
                .background(Color.Transparent)
              ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo principal",
                modifier = Modifier
                    .width(width)
                    .height(height)
                    .fillMaxWidth()
            )
        }
    }


}

