package com.example.pokemonapi.ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pokemonapi.R
import com.example.pokemonapi.domain.bean.ResultPokemonBean

@Composable
fun MainPokemonComponent(listPokemon: List<ResultPokemonBean>, navController: NavController) {
    Column {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            text = "Lista de Pokemones",
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (listPokemon.isNotEmpty()) {
            RecyclerViewList(listPokemon = listPokemon, navController)
        } else {
            Text("Cargando pokemones...")
        }
    }
}

@Composable
fun RecyclerViewList(listPokemon: List<ResultPokemonBean>, navController: NavController) {

    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        items(listPokemon) { pokemones ->
            ItemPokemon(pokemones, navController)
        }
    }
    )

}


@Composable
private fun ItemPokemon(user: ResultPokemonBean, navController: NavController) {
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Color.White,

        modifier = Modifier
            .padding(10.dp)
            .width(180.dp),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logopokebola),
                contentDescription = null,
                modifier = Modifier.size(140.dp)
            )

            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = user.name,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(start = 15.dp)
                    )

                }
                IconButton(
                    onClick = { },
                    modifier = Modifier.background(
                        color = Color.Red,
                        shape = RoundedCornerShape(10.dp)
                    )
                        .size(40.dp)
                ) {
                    Icon(Icons.Default.Add, tint = Color.White, contentDescription = null)
                }
            }
        }
    }
}