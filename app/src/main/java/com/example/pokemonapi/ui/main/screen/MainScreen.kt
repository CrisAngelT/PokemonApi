package com.example.pokemonapi.ui.main.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.colors.ColorTextFieldContainerDefault
import com.example.pokemonapi.commons.link.getPokemonImage
import com.example.pokemonapi.commons.list.ListPokemon.listPreviewPokemon
import com.example.pokemonapi.commons.lottie.LottieLogoLogin
import com.example.pokemonapi.commons.navigation.DetailAppScreen
import com.example.pokemonapi.commons.screens.CircularProgress
import com.example.pokemonapi.commons.screens.TextView
import com.example.pokemonapi.commons.util.replaceWithChar
import com.example.pokemonapi.commons.utilcomponent.LogoPokemon
import com.example.pokemonapi.domain.bean.ResultPokemonBean
import com.example.pokemonapi.ui.main.MainViewModel
import com.example.pokemonapi.ui.main.state.MainState

@Composable
fun MainScreen(navController: NavController) {
    val mainMapViewModel: MainViewModel = hiltViewModel()
    val listPokemon = mainMapViewModel.pokemonList.value
    val query = mainMapViewModel.query.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarMainScreen(query.value) { query ->
                mainMapViewModel.setQuery(query)
            }

        },
        containerColor = Color.Black,
    ) { paddingValues ->
        PokemonContent(listPokemon, navController, paddingValues)


    }
}

@Composable
fun PokemonContent(
    listPokemon: MainState,
    navController: NavController,
    paddingValues: PaddingValues
) {
    if (listPokemon.isLoading) {
        LottieLogoLogin()

    }
    listPokemon.data?.let { data ->
        RecyclerViewList(listPokemon = data, navController, paddingValues)
    }

}

@Preview
@Composable
fun RecyclerViewList(
    listPokemon: List<ResultPokemonBean> = listPreviewPokemon,
    navController: NavController? = null,
    paddingValues: PaddingValues = PaddingValues()
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(paddingValues),
        columns = GridCells.Fixed(2),
        content = {
            itemsIndexed(listPokemon)
            { count, pokemonBean ->
                ItemPokemon(count, pokemonBean, navController)

            }

        }
    )

}

@Preview(showSystemUi = true)
@Composable
private fun ItemPokemon(
    index: Int = 2,
    user: ResultPokemonBean = ResultPokemonBean(),
    navController: NavController? = null
) {
    val idPokemon = index + 1


    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
        ),
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                navController?.navigate(DetailAppScreen(idPokemon.toString()))
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
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
                contentScale = ContentScale.Fit,
                loading = {
                    CircularProgress(idPokemon)
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextView(replaceWithChar(user.name), 18, R.color.white, true)
                Image(
                    painter = painterResource(id = R.drawable.logopokebola),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun TopAppBarMainScreen(value: String = "Search here", onClick: ((String) -> Unit)? = null) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        LogoPokemon(150.dp, 70.dp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Busca tu pokemon",
            color = Color.White,
            modifier = Modifier
                .padding(top = 4.dp),
            fontSize = 16.sp,

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
                value = value,
                onValueChange = {
                    onClick?.invoke(it)
                },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                placeholder = { Text(text = "search", color = Color.Gray) },
                colors = TextFieldDefaults.colors(
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White,
                    focusedContainerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.2f),
                    unfocusedContainerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.2f),
                    disabledContainerColor = ColorTextFieldContainerDefault,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                textStyle = TextStyle(
                    fontSize = 16.sp
                )
            )

        }
    }
}