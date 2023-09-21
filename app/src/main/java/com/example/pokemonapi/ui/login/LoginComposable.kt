package com.example.pokemonapi.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pokemonapi.R
import com.example.pokemonapi.commons.utilcomponent.SimpleOutlinedTextFieldPasswordSample
import com.example.pokemonapi.commons.utilcomponent.buttonDesign


@Composable
fun LoginComposable(navController: NavController) {
    val hop = stringResource(id = R.string.txt_hop)
    val context = LocalContext.current
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
    )
    {
        item {
            Spacer(modifier = Modifier.height(26.dp))

            Text(
                text = hop,
                textAlign = TextAlign.End,
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(35.dp, 0.dp)
                    .clickable {

                    }

            )
            Spacer(modifier = Modifier.height(16.dp))
            imageLogo()
            Spacer(modifier = Modifier.height(26.dp))
            SimpleOutlinedTextFieldSample()
            Spacer(modifier = Modifier.height(10.dp))
            SimpleOutlinedTextFieldPasswordSample()
            Spacer(modifier = Modifier.height(50.dp))
            buttonDesign(loginEnable = true, onClick = { }, color = Color.Black )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun imageLogo() {
    Box(modifier = Modifier.fillMaxWidth())
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo principal",
                modifier = Modifier
                    .width(85.dp)
                    .height(85.dp)
                    .fillMaxWidth()
            )
        }
    }


}

@Composable
fun formEmail() {
    var value by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = value,
        onValueChange = { value = it},
        leadingIcon = {
                      Icon(imageVector = Icons.Default.Email, contentDescription = "")
        },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        label = { Text("correo electronico") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 10.dp)
            .clip(RoundedCornerShape(10.dp),)
    )


}
@Composable
fun formPassword() {
    var value by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = value,
        onValueChange = { value = it},
        leadingIcon = {
            Icon(imageVector = Icons.Default.Settings, contentDescription = "")
        },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        label = { Text("Contraseña") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp)
            .clip(RoundedCornerShape(10.dp),)
    )


}


