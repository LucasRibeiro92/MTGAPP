package com.scout.mtgapp.ui.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.ui.fragment.CardDetailScreen
import com.scout.mtgapp.ui.fragment.CardListScreen
import com.scout.mtgapp.ui.viewmodel.CardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: CardViewModel) {

    var selectedTab by remember { mutableIntStateOf(1) }
    var selectedCard by remember { mutableStateOf<CardResponse?>(null) }  // Para armazenar o card selecionado
    var searchQuery by remember { mutableStateOf("") }

    /*// Chama o método para carregar a carta aleatória ao iniciar
    LaunchedEffect(Unit) {
        viewModel.loadRandomCard()
    }*/

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    SearchBar(
                        query = searchQuery,
                        onQueryChanged = { newQuery -> searchQuery = newQuery },
                        onSearch = {
                            viewModel.searchCards(searchQuery) // Chama a função de busca no ViewModel
                            selectedTab = 0
                        }
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("Cards") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    label = { Text("Details") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                0 -> CardListScreen(viewModel.cards) { cardId ->
                    // Quando o card for clicado, busca o card e navega para a tela de detalhes
                    viewModel.getCard(cardId)
                    selectedCard = viewModel.cards.value?.find { it.id == cardId } // Encontrar o card na lista
                    selectedTab = 1
                }
                1 -> {
                    selectedCard?.let { CardDetailScreen(it) }  // Passa o card selecionado
                }
            }
        }
    }
}

