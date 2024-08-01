package com.example.pokemonapi.ui.main.screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.pokemonapi.commons.features.IndexPokemon.HOME
import com.example.pokemonapi.commons.link.getPokemonImage
import com.example.pokemonapi.commons.list.ListPokemon.listPreviewPokemon
import com.example.pokemonapi.commons.lottie.LottieComposable
import com.example.pokemonapi.commons.navigation.DetailAppScreen
import com.example.pokemonapi.commons.screens.AlertDialogPokemon
import com.example.pokemonapi.commons.screens.CircularProgress
import com.example.pokemonapi.commons.screens.TextView
import com.example.pokemonapi.commons.util.NavigationBarItems
import com.example.pokemonapi.commons.util.isInternetAvailable
import com.example.pokemonapi.commons.util.replaceWithChar
import com.example.pokemonapi.commons.utilcomponent.LogoPokemon
import com.example.pokemonapi.domain.bean.ResultPokemonBean
import com.example.pokemonapi.ui.main.MainViewModel
import com.example.pokemonapi.ui.map.MapScreen
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.utils.noRippleClickable

@Composable
fun MainScreen(navController: NavController) {
    val mainMapViewModel: MainViewModel = hiltViewModel()
    val listPokemon = mainMapViewModel.pokemonList.value
    val query = mainMapViewModel.query.collectAsState()
    if (listPokemon.isLoading) {
        LottieComposable(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        )

    }
    val isInternetAvailable = mainMapViewModel.isInternetAvailable

    if (isInternetAvailable.value) {
        AlertDialogPokemon(
            isInternetAvailable.value,
            "Active su conexión a internet",
            onConfirmClick = {
                mainMapViewModel.clearValidationInternet()
            })

    }
    MainScreenContent(query.value, navController, listPokemon.data) {
        mainMapViewModel.setQuery(it)

    }

}

@Preview
@Composable
fun MainScreenContent(
    value: String = "",
    navController: NavController = NavController(LocalContext.current),
    listPokemon:
    List<ResultPokemonBean>? = listOf(),
    onValueChange: (String) -> Unit = {}
) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    val navigationBarItems = rememberSaveable { NavigationBarItems.entries.toTypedArray() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (selectedIndex == HOME) {
                TopAppBarMainScreen(value) { word ->
                    onValueChange.invoke(word)

                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Card(
                        modifier = Modifier
                            .size(70.dp)
                            .padding(top = 30.dp)
                            .clickable {
                                selectedIndex = 0
                            },
                        shape = RoundedCornerShape(0.dp, 10.dp, 10.dp, 0.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background.copy(
                                alpha = 0.1f
                            ),
                        )
                    ) {
                        Image(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null,
                            modifier = Modifier.padding(start = 15.dp, top = 8.dp)
                        )
                    }
                }

            }

        },
        bottomBar = {
            if (selectedIndex == HOME) {
                AnimatedNavigationBar(
                    modifier = Modifier.height(60.dp), selectedIndex = selectedIndex,
                    ballAnimation = Parabolic(tween(300)),
                    indentAnimation = Height(tween(300)),
                    barColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f),
                    ballColor = Color.Yellow
                ) {
                    navigationBarItems.forEach { items ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .noRippleClickable { selectedIndex = items.ordinal },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier.size(23.dp),
                                painter = painterResource(id = items.icon),
                                contentDescription = null,
                                alpha = if (selectedIndex == items.ordinal) 1f else 0.5f,
                            )
                            TextView(
                                word = if (items.ordinal == 0) "Pokemon" else "Map",
                                12, R.color.white, true, modifierAll = Modifier.padding(top = 37.dp)
                            )


                        }

                    }
                }
            }
        },
        containerColor = Color.Black,
    ) { paddingValues ->
        if (selectedIndex == HOME) {
            PokemonContent(listPokemon, navController, paddingValues)
        } else {
            MapScreen()
        }


    }
}

@Composable
fun PokemonContent(
    listPokemon: List<ResultPokemonBean>?,
    navController: NavController,
    paddingValues: PaddingValues) {

    listPokemon?.let { data ->
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
            items(listPokemon) { pokemonBean ->
                ItemPokemon(pokemonBean, navController)

            }


        }
    )

}


@Composable
fun ItemPokemon(
    pokemonBean: ResultPokemonBean = ResultPokemonBean(),
    navController: NavController? = null
) {
    val mainMapViewModel: MainViewModel = hiltViewModel()
    val context = LocalContext.current
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                if (isInternetAvailable(context)) {
                    navController?.navigate(
                        DetailAppScreen(
                            pokemonBean.idPokemon.toString(),
                            pokemonBean.name
                        )
                    )
                } else {
                    mainMapViewModel.validationInternet()
                }
            },
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
                model = getPokemonImage(pokemonBean.idPokemon.toString()),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                loading = {
                    CircularProgress(pokemonBean.idPokemon)
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextView(replaceWithChar(pokemonBean.name), 18, R.color.white, true)
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
fun TopAppBarMainScreen(value: String = "", onValueChange: (String) -> Unit = {}) {

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
                    onValueChange.invoke(it)
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
