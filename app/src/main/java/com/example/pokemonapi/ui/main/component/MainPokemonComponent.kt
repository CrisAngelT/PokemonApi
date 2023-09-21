package com.example.pokemonapi.ui.main.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokemonapi.R
import com.example.pokemonapi.data.model.bean.ResultPokemonBean

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
fun RecyclerViewList(listPokemon: List<ResultPokemonBean>,navController: NavController) {
    LazyColumn(
        contentPadding =
        PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Log.e("pokemon",listPokemon.size.toString())
        items(listPokemon) { pokemones ->
            ItemPokemon(pokemones,navController)
        }
    }
}



@Composable
private fun ItemPokemon(user: ResultPokemonBean,navController: NavController) {
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(10.dp)
            .width(180.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.url)
                    .crossfade(true)
                    .build(),
                contentDescription = "gaa",
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape),
                placeholder = painterResource(R.drawable.bolbasor),
                error = painterResource(R.drawable.bolbasor)

                ,

            )

            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = user.name,
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = "S/ 150.23",
                        style = TextStyle(
                            color = Color.Red,
                            fontSize = 16.sp
                        )
                    )
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier.background(
                        color = colorResource(id = R.color.color_linear),
                        shape = RoundedCornerShape(10.dp)
                    )
                ) {
                    Icon(Icons.Default.Add, tint = Color.White,  contentDescription = null)
                }
            }
        }
    }
}