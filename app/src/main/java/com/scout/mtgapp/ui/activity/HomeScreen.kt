package com.scout.mtgapp.ui.activity
/*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.scout.mtgapp.ui.fragment.CardDetailScreen
import com.scout.mtgapp.ui.viewmodel.CardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: CardViewModel, onSearch: (String) -> Unit) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MTG App") },
                actions = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        TextField(
                            value = searchQuery,
                            onValueChange = { newText ->
                                searchQuery = newText
                            },
                            placeholder = { Text("Buscar carta...") },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.background,
                                textColor = MaterialTheme.colorScheme.onBackground
                            )
                        )
                        IconButton(onClick = { onSearch(searchQuery.text) }) {
                            Icon(Icons.Default.Search, contentDescription = "Buscar")
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Text("Bottom Navigation")
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val card by viewModel.card.observeAsState()

            if (card != null) {
                CardDetailScreen(card!!)
            } else {
                Text("Carregando carta aleatória...")
            }
        }
    }
}
*/