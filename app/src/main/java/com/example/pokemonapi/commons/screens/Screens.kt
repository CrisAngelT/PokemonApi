package com.example.pokemonapi.commons.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.Slider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.colors.ColorLazyGridItem
import com.example.pokemonapi.commons.colors.ColorPokemonTypeMap
import com.example.pokemonapi.commons.list.TabItem
import com.example.pokemonapi.commons.util.replaceWithChar
import com.exyte.animatednavbar.utils.noRippleClickable
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties


@Preview
@Composable
fun TextView(
    word: String = stringResource(id = R.string.app_name),
    fontSize: Int = 14,
    color: Int = R.color.black,
    isBold: Boolean = false,
    modifierAll: Modifier = Modifier,
) {
    Text(
        text = word,
        modifier = modifierAll,
        fontSize = fontSize.sp,
        textAlign = TextAlign.Center,
        fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
        color = colorResource(id = color)
    )

}

@Composable
fun SpacerViewWithHeight(spacer: Int = 10) {
    Spacer(modifier = Modifier.height(spacer.dp))
}

@Preview
@Composable
fun SliderMinimalExample(
    typePokemon: String = "",
    baseStat: Float = 0.5f,
    modifierAll: Modifier = Modifier
) {
    var sliderPosition by rememberSaveable { mutableFloatStateOf(baseStat) }
    Column(modifier = modifierAll) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            enabled = false,
            colors = androidx.compose.material.SliderDefaults.colors(
                activeTickColor = Color.Black,
                disabledActiveTrackColor = ColorPokemonTypeMap[typePokemon] ?: Color.Yellow,
                thumbColor = Color.Transparent,
                disabledThumbColor = Color.Transparent,
                disabledInactiveTrackColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
            ),


            )

    }
}

@Preview
@Composable
fun CircularProgress(idPokemon: Int = 7) {
    val strokeWidth = 5.dp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            color = ColorLazyGridItem[idPokemon % ColorLazyGridItem.size],
            strokeWidth = strokeWidth
        )
    }
}

@Preview
@Composable
fun TabRowFeatures() {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val tabItem = listOf(
        TabItem(
            title = "Base Stats",
            unSelectedItem = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home
        ), TabItem(
            title = "Evolution chart",
            unSelectedItem = Icons.Outlined.ShoppingCart,
            selectedIcon = Icons.Filled.ShoppingCart
        ), TabItem(
            title = "Training",
            unSelectedItem = Icons.Outlined.Settings,
            selectedIcon = Icons.Filled.Settings
        ),
        TabItem(
            title = "Kitens",
            unSelectedItem = Icons.Outlined.Settings,
            selectedIcon = Icons.Filled.Settings
        )
    )

    val pagerState = rememberPagerState {
        tabItem.size
    }

    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }

    Row(modifier = Modifier.fillMaxWidth()) {

        TabRow(selectedTabIndex = selectedTabIndex,
            containerColor = Color.Black,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .padding(start = 10.dp),
                    height = 4.dp,
                    color = Color.Yellow
                )
            },
            divider = {
                HorizontalDivider(
                    color = Color.Transparent
                )
            }
        ) {
            tabItem.forEachIndexed { index, tabItem ->

                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },

                    selectedContentColor = Color.Yellow,
                    unselectedContentColor = Color.Red,
                    text = { Text(text = tabItem.title, color = Color.White) },


                    )
            }
        }

        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = tabItem[index].title)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExampleCurse() {
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hola")
        Text(text = "Hola")
        Text(text = "Hola")
    }

}

@Preview
@Composable
fun MyBox(

) {
    val value by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        ), label = ""
    )
    val colors = listOf(
        Color.Black,Color.Yellow
    )
    var gradientBrush by remember {
        mutableStateOf(
            Brush.horizontalGradient(
                colors = colors,
                startX = -10.0f,
                endX = 400.0f,
                tileMode = TileMode.Repeated
            )
        )
    }
    OutlinedButton(
        onClick = { },
        modifier = Modifier.width(100.dp).drawBehind { rotate(value){

            drawOval(
                gradientBrush, style = Stroke(width = 12.dp.value)
            )
        } },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
        ),
        border = BorderStroke(
            2.dp,
            color = ColorPokemonTypeMap["poison"] ?: Color.Yellow
        )
    ) {
        TextView(
            replaceWithChar("bakuno"),
            14, R.color.white
        )
    }


}

@Composable
fun chooseDesignMapByJson(json: String): MapProperties {
    val properties by remember {
        mutableStateOf(
            MapProperties(
                mapStyleOptions = MapStyleOptions(
                    json
                )
            )
        )
    }
    return properties
}

