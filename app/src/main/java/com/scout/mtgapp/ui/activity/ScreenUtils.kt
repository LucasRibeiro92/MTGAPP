package com.scout.mtgapp.ui.activity

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    // Implementa um campo de texto para a busca
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        label = { Text("Search for cards...") },
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = { onSearch() }) {
                Icon(Icons.Default.Search, contentDescription = "Search Icon")
            }
        }
    )
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(title = { Text("MTG App") })
}

@Composable
fun SearchViewComponent(onSearchSubmit: (String) -> Unit) {
    var query by remember { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = { query = it },
        placeholder = { Text("Search cards...") },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = { onSearchSubmit(query) }) {
                Icon(Icons.Default.Search, contentDescription = "Search Icon")
            }
        }
    )
}

@Composable
fun BottomBarComponent() {
    BottomAppBar {
        // Itens da bottom bar
        IconButton(onClick = { /* ação da bottom bar */ }) {
            Icon(Icons.Default.Home, contentDescription = "Home")
        }
        IconButton(onClick = { /* ação da bottom bar */ }) {
            Icon(Icons.Default.List, contentDescription = "Card List")
        }
        IconButton(onClick = { /* ação da bottom bar */ }) {
            Icon(Icons.Default.Favorite, contentDescription = "Favorites")
        }
    }
}
*/
