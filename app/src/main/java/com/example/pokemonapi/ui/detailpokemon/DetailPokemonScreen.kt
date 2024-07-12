package com.example.pokemonapi.ui.detailpokemon

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.colors.ColorPokemonTypeMap
import com.example.pokemonapi.commons.constants.Constants.Companion.DETAIL_POKEMON_OBJECT
import com.example.pokemonapi.commons.link.getPokemonImage
import com.example.pokemonapi.commons.lottie.LottieLogoLogin
import com.example.pokemonapi.commons.navigation.DetailAppScreen
import com.example.pokemonapi.commons.player.UtilMediaPlayer
import com.example.pokemonapi.commons.preference.Preference
import com.example.pokemonapi.commons.screens.SliderMinimalExample
import com.example.pokemonapi.commons.screens.SpacerViewWithHeight
import com.example.pokemonapi.commons.screens.TextView
import com.example.pokemonapi.commons.util.dividerHeightAndWithPokemon
import com.example.pokemonapi.commons.util.dividerWithStats
import com.example.pokemonapi.commons.util.replaceWithChar
import com.example.pokemonapi.data.model.response.PokemonInfoResponse
import com.example.pokemonapi.ui.detailpokemon.state.DetailState
import timber.log.Timber


@Composable
fun DetailPokemonScreen(
    navController: NavController,
    idPokemon: DetailAppScreen,

    ) {
    val detailViewModel: DetailPokemonViewModel = hiltViewModel()
    val response = detailViewModel.detailPokemon.value

    if (idPokemon.data.isNotEmpty()) {
        LaunchedEffect(true)
        {
            detailViewModel.getInfoPokemon(idPokemon.data.toInt())

        }

    }
    DetailPokemonContent(response, idPokemon.data.toInt())

}


@Composable
fun DetailPokemonContent(response: DetailState, idPokemon: Int) {
    if (response.isLoading) {
        LottieLogoLogin()
    } else if (response.data != null) {
        DataPokemon(idPokemon, response.data)
    }
}

@Preview
@Composable
fun DataPokemon(
    idPokemon: Int = 2,
    response: PokemonInfoResponse? = null,
    navController: NavController? = null
) {
    Timber.e("POKEMON RESPONSE  --> $response")
    val totalCP = response?.stats?.sumOf { it.baseStat } ?: 0
    val typePokemon = response?.types?.firstOrNull()?.type?.name ?: "unknown"
    val context  = LocalContext.current
    UtilMediaPlayer().play()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = { TopAppBar(totalCP, navController) },
        containerColor = Color.Black
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        )
        {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .padding(20.dp, 0.dp),
                alignment = Alignment.Center,
                model = getPokemonImage(idPokemon.toString()),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                loading = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .width(130.dp)
                    )
                }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextView(
                    replaceWithChar(response?.name?:"Makanaky"),
                    40,
                    R.color.white,
                    true,
                )
                Icon(painter = painterResource(id = R.drawable.svg_sound), contentDescription = null,
                    tint = Color.White, modifier = Modifier.padding(start = 5.dp).size(30.dp).clickable {
                        UtilMediaPlayer().playAudioFromUrl(response?.cries?.latest.orEmpty(),context)
                    })


            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally)
            ) {
                response?.types?.forEach { data ->

                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.width(100.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
                        ),
                        border = BorderStroke(2.dp,color = ColorPokemonTypeMap[data.type.name] ?: Color.Yellow)
                    ) {
                        TextView(
                            replaceWithChar(data.type.name),
                            14, R.color.white
                        )
                    }

                }

            }
            SpacerViewWithHeight()
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
                )
            ) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column {
                            TextView(
                                dividerHeightAndWithPokemon(response?.weight ?: 0) + "KG",
                                20,
                                R.color.white,
                                true
                            )
                            SpacerViewWithHeight()
                            TextView("WEIGHT", 16, R.color.white, true)

                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            SpacerViewWithHeight()
                            TextView("/", 20, R.color.white, true)

                        }
                        Column {
                            TextView(
                                dividerHeightAndWithPokemon(response?.height ?: 0) + "M",
                                20,
                                R.color.white,
                                true
                            )
                            SpacerViewWithHeight()
                            TextView("HEIGHT", 16, R.color.white, true)

                        }


                    }



                }

            }
            SpacerViewWithHeight()
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
                )
            ) {
                Column(modifier = Modifier.padding(15.dp)) {
                    response?.stats?.forEach { data ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                20.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextView(
                                replaceWithChar(data.stat.name),
                                14, R.color.white,
                                true,
                                modifier = Modifier.width(130.dp)
                            )
                            TextView(
                                data.baseStat.toString().replaceFirstChar { it.uppercase() },
                                14, R.color.white
                            )
                            SliderMinimalExample(typePokemon,dividerWithStats(data.baseStat))

                        }
                    }

                    SpacerViewWithHeight(20)


                }

            }
        }
    }

}

@Preview(showSystemUi = true, showBackground = true, uiMode = 1)
@Composable
fun TopAppBar(totalCP: Int = 2, navController: NavController? = null) {
    Column(modifier = Modifier.padding(top = 30.dp, start = 15.dp, end = 15.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
            Icon(
                imageVector = Icons.Rounded.FavoriteBorder,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        navController?.popBackStack()
                    }
            )

        }
        Spacer(modifier = Modifier.height(10.dp))
        TextView("$totalCP CP", 40, R.color.white, true)

    }
}