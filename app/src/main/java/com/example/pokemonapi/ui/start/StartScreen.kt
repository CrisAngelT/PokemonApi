package com.example.pokemonapi.ui.start


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.navigation.AppScreen
import com.example.pokemonapi.commons.utilcomponent.SetStatusBarColor
import com.example.pokemonapi.commons.utilcomponent.LogoPokemon

@Preview
@Composable
fun StartScreen(navController: NavController? = null) {
    SetStatusBarColor(Color.Black)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White,
            )
    ) {

        Image(
            painter = painterResource(id = R.drawable.walpaper_pokemon),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 30.dp)

        ) {

            LogoPokemon(200.dp,85.dp)


        }
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)

        ) {

            androidx.compose.material3.Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.color_glass),
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(20.dp)
                    .padding(bottom = 120.dp)
                    .clickable {
                        navController?.navigate(AppScreen.MainScreen.route)
                    },

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 30.dp,
                ),
            ) {
                androidx.compose.material3.Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = "Iniciar",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 26.sp,
                )
            }


        }

    }


}



