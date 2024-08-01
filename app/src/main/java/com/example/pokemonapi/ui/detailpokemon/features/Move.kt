package com.example.pokemonapi.ui.detailpokemon.features

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.colors.ColorPokemonTypeMap
import com.example.pokemonapi.commons.screens.SpacerViewWithHeight
import com.example.pokemonapi.commons.screens.TextView
import com.example.pokemonapi.commons.util.replaceWithChar
import com.example.pokemonapi.domain.bean.move.MoveBean

@Preview
@Composable
fun MovePokemon(types: List<String> = listOf(), moveBean: MoveBean = MoveBean()) {
    val color = types.map { name -> ColorPokemonTypeMap[name] ?: Color.Yellow }
    Column {
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
                    .padding(15.dp)
            ) {
                TextView(
                    moveBean.spanishFlavorText,
                    14,
                    R.color.white,
                    true
                )
                SpacerViewWithHeight()
                moveBean.combosBean?.let { combos ->
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        item {
                            TextView("Combos", 14, R.color.white, true)
                            combos.combosUseAfter?.forEach { name ->
                                OutlinedButton(
                                    onClick = { },
                                    modifier = Modifier.padding(10.dp),
                                    shape = MaterialTheme.shapes.small,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent
                                    ),
                                    border = BorderStroke(
                                        2.dp,
                                        color = color[0]
                                    )
                                ) {
                                    TextView(
                                        replaceWithChar(name),
                                        14, R.color.white
                                    )
                                }
                            }
                            combos.combosUseBefore?.forEach { name ->
                                OutlinedButton(
                                    onClick = { },
                                    modifier = Modifier.padding(8.dp),
                                    shape = MaterialTheme.shapes.small,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent
                                    ),
                                    border = BorderStroke(
                                        2.dp,
                                        color = color[0]
                                    )
                                ) {
                                    TextView(
                                        replaceWithChar(name),
                                        14, R.color.white
                                    )
                                }
                            }
                        }
                    }
                }

                SpacerViewWithHeight()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        TextView(stringResource(id = R.string.txt_power), 14, R.color.white, true)
                        Spacer(modifier = Modifier.height(10.dp))
                        TextView(
                            stringResource(id = R.string.txt_accuracy),
                            14,
                            R.color.white,
                            true
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        TextView(stringResource(id = R.string.txt_pp), 14, R.color.white, true)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    Column {
                        TextView(moveBean.power.toString(), 14, R.color.white, true)

                        Spacer(modifier = Modifier.height(10.dp))
                        TextView(moveBean.accuracy.toString(), 14, R.color.white, true)

                        Spacer(modifier = Modifier.height(10.dp))
                        TextView(moveBean.pp.toString(), 14, R.color.white, true)
                        Spacer(modifier = Modifier.height(10.dp))

                    }


                }

            }
        }
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
                "Sprites",
                28,
                R.color.white,
                true, modifierAll = Modifier
                    .padding(top = 5.dp, bottom = 5.dp)
                    .fillMaxWidth()
            )
        }

    }


}

@Preview
@Composable
fun ItemSprites(sprites: String = "Bolbasor") {
    Column {

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
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = sprites,
                    contentDescription = null,
                    modifier = Modifier.size(70.dp)
                )

            }
        }
    }
}


