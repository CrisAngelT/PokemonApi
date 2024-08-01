package com.example.pokemonapi.ui.detailpokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.features.FeaturesPokemon.BASE_STATS
import com.example.pokemonapi.commons.features.FeaturesPokemon.EVOLUTION_CHART
import com.example.pokemonapi.commons.features.FeaturesPokemon.MOVE
import com.example.pokemonapi.commons.link.getPokemonImage
import com.example.pokemonapi.commons.lottie.LottieComposable
import com.example.pokemonapi.commons.navigation.DetailAppScreen
import com.example.pokemonapi.commons.player.UtilMediaPlayer
import com.example.pokemonapi.commons.screens.TextView
import com.example.pokemonapi.domain.bean.DetailPokemonBean
import com.example.pokemonapi.ui.detailpokemon.features.BaseStats
import com.example.pokemonapi.ui.detailpokemon.features.ItemEvolutionChart
import com.example.pokemonapi.ui.detailpokemon.features.ItemSprites
import com.example.pokemonapi.ui.detailpokemon.features.MovePokemon

@Composable
fun DetailPokemonScreen(
    navController: NavController,
    dataPokemon: DetailAppScreen
) {
    val detailViewModel: DetailPokemonViewModel = hiltViewModel()
    val response = detailViewModel.state
    val incremental = detailViewModel.indexFeaturesPokemon.value
    val idPokemonWithEvolution = detailViewModel.state.speciesResponse?.idPokemonWithEvolution ?: 0
    when (incremental) {
        BASE_STATS -> {
            LaunchedEffect(true) {
                detailViewModel.getInfoPokemon(dataPokemon.idPokemon.toInt())
                detailViewModel.getSpeciesPokemonByName(dataPokemon.namePokemon)
            }
        }

        EVOLUTION_CHART -> {
            LaunchedEffect(true) {
                detailViewModel.getEvolutionPokemonById(idPokemonWithEvolution)

            }
        }

        MOVE -> {
            LaunchedEffect(true) {
                detailViewModel.getMovePokemonById(dataPokemon.idPokemon.toInt())

            }
        }


    }

    if (response.isLoading) {
        LottieComposable(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black))
    } else if (response.data != null) {
        UtilMediaPlayer().play()
        DataPokemon(dataPokemon.idPokemon.toInt(), response.data, navController, detailViewModel)
    }

}


@Preview
@Composable
fun DataPokemon(
    idPokemon: Int = 2,
    response: DetailPokemonBean? = null,
    navController: NavController? = null,
    detailViewModel: DetailPokemonViewModel? = null
) {
    val indexFeatures = detailViewModel?.indexFeaturesPokemon?.value ?: 0
    val listEvolution = detailViewModel?.state?.evolutionResponse?.listPokemon ?: listOf()
    val move = detailViewModel?.state?.moveResponse
    val listSprites = detailViewModel?.state?.data?.sprites ?: emptyList()
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = { TopAppBar(response, navController) },
        containerColor = Color.Black
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item(span = { GridItemSpan(2) }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 3.dp, end = 3.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (indexFeatures >= 1) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Card(
                                onClick = { detailViewModel?.indexDecrementalFeaturePokemon() },
                                modifier = Modifier.size(45.dp),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
                                )
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.svg_arrow_right),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .padding(10.dp),
                                    colorFilter = ColorFilter.tint(Color.White)
                                )
                            }
                        }
                    }


                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .weight(6f),
                        model = getPokemonImage(idPokemon.toString()),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        loading = {
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = null,
                            )
                        }
                    )
                    if (indexFeatures != MOVE){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                onClick = { detailViewModel?.indexIncrementalFeaturePokemon() },
                                modifier = Modifier.size(45.dp),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
                                )
                            ) {

                                Image(
                                    painter = painterResource(id = R.drawable.svg_arrow_left),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .padding(10.dp),
                                    colorFilter = ColorFilter.tint(Color.White)
                                )
                            }
                        }
                    }



                }
            }
            item(span = { GridItemSpan(2) }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    when (indexFeatures) {
                        BASE_STATS -> {
                            TextView(
                                response?.namePokemon ?: "Bulbasaur",
                                32,
                                R.color.white,
                                true,
                            )
                            Icon(painter = painterResource(id = R.drawable.svg_sound),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .size(30.dp)
                                    .clickable {
                                        UtilMediaPlayer().playAudioFromUrl(
                                            response?.soundPokemon.orEmpty(),
                                            context
                                        )
                                    })
                        }

                        EVOLUTION_CHART -> {
                            if (listEvolution.isNotEmpty()) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 30.dp, end = 30.dp, bottom = 10.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.background.copy(
                                            alpha = 0.1f
                                        )
                                    )
                                ) {
                                    TextView(
                                        "Evolution chart",
                                        30,
                                        R.color.white,
                                        true,
                                        modifierAll = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp)
                                    )
                                }

                            }

                        }

                        MOVE -> {
                            move?.let {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 30.dp, end = 30.dp, bottom = 10.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.background.copy(
                                            alpha = 0.1f
                                        )
                                    )
                                ) {
                                    TextView(
                                        "Moves",
                                        30,
                                        R.color.white,
                                        true,
                                        modifierAll = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp)
                                    )
                                }

                            }

                        }
                    }


                }
            }
            when (indexFeatures) {
                BASE_STATS -> {
                    item(span = { GridItemSpan(2) }) {
                        BaseStats(response?.types ?: listOf(), response)

                    }
                }

                EVOLUTION_CHART -> {
                    items(listEvolution) { response ->
                        ItemEvolutionChart(response)
                    }
                }

                MOVE -> {
                    move?.let { moveBean ->
                        item(span = { GridItemSpan(2) }) {
                            MovePokemon(response?.types ?: listOf(), moveBean)

                        }
                    }
                    items(listSprites) { sprites ->
                        ItemSprites(sprites)

                    }


                }
            }


        }


    }

}

@Preview(showSystemUi = true, showBackground = true, uiMode = 0)
@Composable
fun TopAppBar(
    response: DetailPokemonBean? = null,
    navController: NavController? = null
) {
    val totalCP = response?.totalCp ?: 0
    Column(modifier = Modifier.padding(top = 10.dp, start = 15.dp, end = 15.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(onClick = { navController?.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }


        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextView("$totalCP CP", 36, R.color.white, true)


        }

    }
}