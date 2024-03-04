package com.example.pokemonapi.ui.main.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.colors.ColorLazyGridItem
import com.example.pokemonapi.commons.colors.ColorTextFieldContainerDefault
import com.example.pokemonapi.commons.colors.ColorTextFieldText
import com.example.pokemonapi.commons.constants.Constants.Companion.DETAIL_POKEMON_OBJECT
import com.example.pokemonapi.commons.link.getPokemonImage
import com.example.pokemonapi.commons.lottie.LottieLogoLogin
import com.example.pokemonapi.commons.navigation.AppScreen
import com.example.pokemonapi.commons.preference.Preference
import com.example.pokemonapi.commons.utilcomponent.LogoPokemon
import com.example.pokemonapi.domain.bean.ResultPokemonBean
import com.example.pokemonapi.ui.main.MainViewModel
import java.util.Locale


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(mainMapViewModel: MainViewModel, navController: NavController) {
    val listPokemon = mainMapViewModel.pokemonList.value
    val query = mainMapViewModel.query.collectAsState()
    Scaffold(
        topBar = {

            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp)
            ) {
                LogoPokemon(150.dp, 70.dp)
                Spacer(modifier = Modifier.height(10.dp))
                androidx.compose.material3.Text(
                    text = "Busca tu pokemona",
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 4.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                )
                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp)
                ) {
                    TextField(
                        modifier = Modifier
                            .weight(4f),
                        value = query.value,
                        onValueChange = {
                            mainMapViewModel.setQuery(it)
                        },
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        leadingIcon = {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        },
                        placeholder = { "Picachu" },
                        colors = TextFieldDefaults.colors(
                            unfocusedTextColor = ColorTextFieldText,
                            focusedContainerColor = ColorTextFieldContainerDefault,
                            unfocusedContainerColor = ColorTextFieldContainerDefault,
                            disabledContainerColor = ColorTextFieldContainerDefault,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        ),
                    )

                }
            }
        }
    ) {
        Column(modifier = Modifier.background(Color.White)) {

            if (listPokemon.isLoading) {
                LottieLogoLogin()

            }
            listPokemon.data?.let {
                val list = listPokemon.data
                if (list.isNotEmpty()) {
                    RecyclerViewList(listPokemon = list, navController)
                }
            }


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
    val idPokemon = index + 1
    val context = LocalContext.current
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = ColorLazyGridItem[idPokemon % ColorLazyGridItem.size],
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                Preference.fnWrite(context, DETAIL_POKEMON_OBJECT, idPokemon.toString())
                navController.navigate(AppScreen.DetailScreen.route)
            },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                alignment = Alignment.Center,
                model = getPokemonImage(idPokemon.toString()),
                contentDescription = null,
                contentScale = ContentScale.Fit
                )

            Row(modifier = Modifier.padding(top = 10.dp)) {
                Column(modifier = Modifier.weight(1.2f)) {
                    Text(
                        text = user.name.uppercase(Locale.ROOT),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 15.sp
                        ),
                        modifier = Modifier.padding(start = 15.dp)
                    )

                }
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .background(
                            color = ColorLazyGridItem[index % ColorLazyGridItem.size],
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(40.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logopokebola),
                        contentDescription = null,
                        modifier = Modifier
                            .width(45.dp)
                            .height(45.dp)
                    )
                }
            }
        }
    }
}