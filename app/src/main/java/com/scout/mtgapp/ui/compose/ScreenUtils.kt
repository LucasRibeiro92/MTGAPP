package com.scout.mtgapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.scout.mtgapp.ui.theme.BrightRed


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
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp,
                    bottomStart = 8.dp,
                    bottomEnd = 8.dp
                )
            ),
        trailingIcon = {
            IconButton(onClick = { onSearch() }) {
                Icon(Icons.Default.Search, contentDescription = "Search Icon")
            }
        }
    )
}

@Composable
fun GradientBox(
    modifier: Modifier
) {
    Box(
        modifier = modifier.background(brush = Brush.linearGradient(
            listOf(
                BrightRed,
                Color.Black
            )
        ))
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
