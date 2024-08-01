package com.example.pokemonapi.ui.map

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.firebase.PokemonLocation
import com.example.pokemonapi.commons.gson.BeanMapper
import com.example.pokemonapi.commons.lottie.LottieComposable
import com.example.pokemonapi.commons.screens.SliderMinimalExample
import com.example.pokemonapi.commons.screens.TextView
import com.example.pokemonapi.commons.screens.chooseDesignMapByJson
import com.example.pokemonapi.commons.util.bitmapDescriptorFromSvgUrl
import com.example.pokemonapi.commons.util.json.styleJson
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import timber.log.Timber
import com.google.maps.android.compose.*

@SuppressLint("SuspiciousIndentation")
@Composable
fun MapScreen(viewModel: MapViewModel = viewModel()) {
    val mapState by viewModel.mapState
    LaunchedEffect(true) {
        viewModel.getCoordinateFirebase()
    }

    MapScreenPreview(mapState.listPokemon)

}

@Preview()
@Composable
fun MapScreenPreview(listPokemon: List<PokemonLocation?> = emptyList()) {
    Scaffold(containerColor = Color.Black) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Timber.e("MAP STATE --> ${BeanMapper.toJson(listPokemon)}")
            if (listPokemon.isNotEmpty()) {
                GoogleMap(listPokemon)
                LottieComposable(
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .size(60.dp)
                        .align(Alignment.BottomCenter), R.raw.pokebola
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 20.dp, end = 10.dp)

                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    painter = painterResource(id = R.drawable.pikachu),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(CircleShape)
                                )
                                TextView(
                                    "20",
                                    20,
                                    R.color.white,
                                    true,
                                    modifierAll = Modifier.padding(top = 15.dp)
                                )

                            }

                            Image(
                                painter = painterResource(id = R.drawable.ash),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                            )

                        }

                        SliderMinimalExample(
                            "poison", 0.5f,
                            Modifier
                                .width(90.dp)
                                .height(10.dp)
                        )
                        TextView("User", 14, R.color.white, true)
                    }


                }
            } else {
                LottieComposable(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                )
            }

        }

    }
}


@Composable
fun GoogleMap(
    coordinatePokemon: List<PokemonLocation?> = emptyList(),
) {
    val firstLocation = coordinatePokemon[1]?.location
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(
            LatLng(firstLocation?.latitude ?: 0.0, firstLocation?.longitude ?: 0.0),
            16f, // Zoom level
            90f, // Tilt
            314f // Bearing
        )
    }
    val pokemonLocations = rememberSaveable { coordinatePokemon }
    val context = LocalContext.current
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        uiSettings = MapUiSettings(compassEnabled = false, zoomControlsEnabled = false),
        cameraPositionState = cameraPositionState,
        properties = chooseDesignMapByJson(styleJson)
    ) {

        pokemonLocations.forEach { pokemonLocation ->
            pokemonLocation?.location?.let { coordinates ->
                val bitmapDescriptor = remember { mutableStateOf<BitmapDescriptor?>(null) }
                LaunchedEffect(true) {
                    Timber.e("IMAGE --> ${pokemonLocation.image}")
                    bitmapDescriptor.value =
                        bitmapDescriptorFromSvgUrl(context, pokemonLocation.image ?: "", 200, 200)
                }
                bitmapDescriptor.value?.let {
                    Marker(
                        state = MarkerState(
                            position = LatLng(
                                coordinates.latitude, coordinates.longitude
                            )
                        ), title = pokemonLocation.name, snippet = "¡Atrápalo!", icon = it
                    )
                }


            }

        }


    }
}
