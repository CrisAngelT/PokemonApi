package com.example.pokemonapi.ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.Constants.Companion.DETAIL_POKEMON_OBJECT
import com.example.pokemonapi.commons.Resource
import com.example.pokemonapi.commons.gson.BeanMapper
import com.example.pokemonapi.commons.lottie.LottieLogoLogin
import com.example.pokemonapi.commons.navigation.AppScreen
import com.example.pokemonapi.commons.preference.Preference
import com.example.pokemonapi.domain.bean.Pokemon
import com.example.pokemonapi.domain.bean.ResultPokemonBean
import com.example.pokemonapi.ui.main.MainViewModel
import timber.log.Timber

@Composable
fun MainPokemonComponent(mainMapViewModel: MainViewModel, navController: NavController) {
    val listPokemon by mainMapViewModel.mutablePokemonResponse.observeAsState(initial = null)

    LaunchedEffect(key1 = true)
    {
        mainMapViewModel.onCreate()
    }
    Column(modifier = Modifier.background(Color.White)) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            text = "Lista de Pokemones",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        when (listPokemon) {
            is Resource.Loading -> {
                LottieLogoLogin()
            }
            is Resource.Success -> {
                val list = listPokemon?.data?.results ?: listOf()
                if (list.isNotEmpty()) {
                    RecyclerViewList(listPokemon = list, navController)
                } else {
                    Text("Cargando pokemones...")
                }
            }
            else -> {}
        }

    }
}

@Composable
fun RecyclerViewList(listPokemon: List<ResultPokemonBean>, navController: NavController) {

    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        itemsIndexed(listPokemon)
        { count, pokemones ->
            ItemPokemon(count, pokemones, navController)

        }

    }
    )

}


@Composable
private fun ItemPokemon(index: Int, user: ResultPokemonBean, navController: NavController) {
    val context = LocalContext.current
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Color.White,

        modifier = Modifier
            .padding(10.dp)
            .width(180.dp),
        elevation = 10.dp
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
                    onClick = {
                        Timber.e("INDEXXXX ->$index")
                        Preference.fnWrite(context, DETAIL_POKEMON_OBJECT, index.toString())
                        navController.navigate(AppScreen.DetailScreen.route)
                    },
                    modifier = Modifier
                        .background(
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