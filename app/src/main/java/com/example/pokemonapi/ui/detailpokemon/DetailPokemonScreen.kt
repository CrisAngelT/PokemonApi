package com.example.pokemonapi.ui.detailpokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pokemonapi.commons.colors.ColorLazyGridItem
import com.example.pokemonapi.commons.constants.Constants.Companion.DETAIL_POKEMON_OBJECT
import com.example.pokemonapi.commons.gson.BeanMapper
import com.example.pokemonapi.commons.link.getPokemonImage
import com.example.pokemonapi.commons.lottie.LottieLogoLogin
import com.example.pokemonapi.commons.preference.Preference
import com.example.pokemonapi.commons.utilcomponent.SetStatusBarColor
import com.example.pokemonapi.ui.detailpokemon.state.DetailState
import timber.log.Timber


@Composable
fun DetailPokemonScreen(navController: NavController, detailViewModel: DetailPokemonViewModel) {
    val response = detailViewModel.detailPokemon.value
    val context = LocalContext.current
    val getPreference = Preference.fnRead(context, DETAIL_POKEMON_OBJECT) ?: ""
    if (getPreference.isNotEmpty()) {
        LaunchedEffect(true)
        {
            detailViewModel.getInfoPokemon(getPreference.toInt())

        }

    }
    if (response.isLoading) {
        LottieLogoLogin()
    }
    if (response.data != null) {
        DataPokemon(getPreference.toInt(), response)

    }

}


@Composable
fun DataPokemon(idPokemon: Int, response: DetailState) {
    Timber.e("IDPOKEMON -> $idPokemon")
    Timber.e("DATA ---> ${BeanMapper.toJson(response.data?.sprites?.other?.dreamWorld?.frontDefault)}")
    SetStatusBarColor(ColorLazyGridItem[idPokemon % ColorLazyGridItem.size])

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorLazyGridItem[idPokemon % ColorLazyGridItem.size])
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = ColorLazyGridItem[idPokemon % ColorLazyGridItem.size],
                    shape = RoundedCornerShape(25.dp, 5.dp, 25.dp, 5.dp)
                )
                .align(Alignment.CenterStart)
                .padding(top = 40.dp),
        ) {


            AsyncImage(
                model = getPokemonImage(idPokemon.toString()),
                contentDescription = "gaaa",
                modifier = Modifier
                    .height(150.dp)
                    .padding(20.dp, 0.dp)
                    .fillMaxWidth(),

                )
            Text(
                text = response.data?.name.toString(),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )

        }
    }
}