package com.example.pokemonapi.ui.detailpokemon.features

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.colors.ColorPokemonTypeMap
import com.example.pokemonapi.commons.screens.SliderMinimalExample
import com.example.pokemonapi.commons.screens.SpacerViewWithHeight
import com.example.pokemonapi.commons.screens.TextView
import com.example.pokemonapi.commons.util.dividerHeightAndWithPokemon
import com.example.pokemonapi.commons.util.dividerWithStats
import com.example.pokemonapi.commons.util.replaceWithChar
import com.example.pokemonapi.domain.bean.DetailPokemonBean

@Preview
@Composable
fun BaseStats(types:List<String> = listOf(),response:DetailPokemonBean?=null){
    val typePokemon = response?.types?.firstOrNull() ?: "unknown"

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally)
        ) {
            types.forEach { name ->
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.width(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
                    ),
                    border = BorderStroke(
                        2.dp,
                        color = ColorPokemonTypeMap[name] ?: Color.Yellow
                    )
                ) {
                    TextView(
                        replaceWithChar(name),
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
                            Modifier.width(130.dp)
                        )
                        TextView(
                            data.baseStat.toString().replaceFirstChar { it.uppercase() },
                            14, R.color.white
                        )
                        SliderMinimalExample(typePokemon, dividerWithStats(data.baseStat))

                    }
                }

                SpacerViewWithHeight(20)


            }

        }
    }

}