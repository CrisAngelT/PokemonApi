package com.example.pokemonapi.ui.detailpokemon.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.link.getPokemonImage
import com.example.pokemonapi.commons.link.getPokemonShinyImage
import com.example.pokemonapi.commons.link.getSvgPokemonImage
import com.example.pokemonapi.commons.screens.CircularProgress
import com.example.pokemonapi.commons.screens.TextView
import com.example.pokemonapi.commons.util.replaceWithChar
import com.example.pokemonapi.domain.bean.evolution.ContentEvolutionPokemonBean
import timber.log.Timber


@Composable
fun EvolutionChart(listEvolutionPokemon: List<ContentEvolutionPokemonBean> = listOf()){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(listEvolutionPokemon) { evolutionPokemon ->
                ItemEvolutionChart(evolutionPokemon)

            }


        }
    )
}
@Preview(device = Devices.NEXUS_5X)
@Composable
fun ItemEvolutionChart(evolutionPokemon: ContentEvolutionPokemonBean = ContentEvolutionPokemonBean()) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
        ),
        modifier = Modifier
            .padding(start = 15.dp, top = 10.dp, end = 15.dp, bottom = 20.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
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
                model = getPokemonImage(evolutionPokemon.idPokemon.toString()),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                loading = {
                    CircularProgress(evolutionPokemon.idPokemon)
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextView(replaceWithChar(evolutionPokemon.name), 18, R.color.white, true)

            }
        }
    }
}
