package com.example.pokemonapi.commons.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import com.example.pokemonapi.commons.list.TabItem

object ListItem {
    val tabItem = listOf(
        TabItem(
            title = "Home", unSelectedItem = Icons.Outlined.Home, selectedIcon = Icons.Filled.Home
        ), TabItem(
            title = "Browse",
            unSelectedItem = Icons.Outlined.ShoppingCart,
            selectedIcon = Icons.Filled.ShoppingCart
        ), TabItem(
            title = "Settings",
            unSelectedItem = Icons.Outlined.Settings,
            selectedIcon = Icons.Filled.Settings
        )
    )
}