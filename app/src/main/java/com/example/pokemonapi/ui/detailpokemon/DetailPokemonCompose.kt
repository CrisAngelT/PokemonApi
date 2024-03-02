package com.example.pokemonapi.ui.detailpokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.Constants.Companion.DETAIL_POKEMON_OBJECT
import com.example.pokemonapi.commons.gson.BeanMapper
import com.example.pokemonapi.commons.preference.Preference
import com.example.pokemonapi.domain.bean.ResultPokemonBean
import timber.log.Timber


@Composable
fun detailPokemonComponent(navController: NavController, detailViewModel: DetailPokemonViewModel) {
    val response by detailViewModel.liveDataInfoPokemon.observeAsState(initial = null)

    val context = LocalContext.current
    val getPreference = Preference.fnRead(context, DETAIL_POKEMON_OBJECT) ?: ""
    if (getPreference.isNotEmpty()) {
        LaunchedEffect(key1 = true)
        {
            detailViewModel.getInfoPokemon(getPreference.toInt())

        }

    }

    Timber.e("RESPONSE --- ${BeanMapper.toJson(response)}")
    pokemon()
}

@Preview
@Composable
fun pokemon() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(25.dp, 5.dp, 25.dp, 5.dp)
                )
                .align(Alignment.CenterStart)
                .padding(top = 40.dp),
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(80.dp)
                    .padding(20.dp, 0.dp)
                    .fillMaxWidth(),

                )


        }
    }
}