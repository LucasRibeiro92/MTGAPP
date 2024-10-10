package com.scout.mtgapp.ui.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.ui.fragment.CardDetailScreen
import com.scout.mtgapp.ui.fragment.CardListScreen
import com.scout.mtgapp.ui.theme.BrightRed
import com.scout.mtgapp.ui.viewmodel.CardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: CardViewModel) {

    var selectedTab by remember { mutableIntStateOf(1) }
    var selectedCard by remember { mutableStateOf<CardResponse?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    // Carrega uma carta aleatória ao iniciar o Composable
    LaunchedEffect(Unit) {
        viewModel.loadRandomCard() // Carrega a carta aleatória
    }

    // Observa o card carregado aleatoriamente no ViewModel
    val randomCard by viewModel.randomCard.observeAsState()

    // Se o card aleatório for carregado, atualiza o card selecionado
    LaunchedEffect(randomCard) {
        randomCard?.let {
            selectedCard = it
            selectedTab = 1 // Muda para a aba de detalhes da carta
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp
                        )
                    )
                    .background(brush = Brush.linearGradient(
                        listOf(
                            BrightRed,
                            Color.Black
                        )
                    ))
                    .padding(bottom = 8.dp) // Espaçamento para separar o título da SearchBar
            ) {
                // Título no centro com 25sp
                Text(
                    text = "Magic App",
                    fontSize = 35.sp,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp)
                        .wrapContentSize(Alignment.Center) // Centraliza o título
                )

                // Barra de pesquisa logo abaixo do título
                SearchBar(
                    query = searchQuery,
                    onQueryChanged = { newQuery -> searchQuery = newQuery },
                    onSearch = {
                        viewModel.searchCards(searchQuery) // Chama a função de busca no ViewModel
                        selectedTab = 0
                    }
                )
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = null) },
                    label = { Text("Search") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("Saved Cards") },
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
                    viewModel.getCard(cardId)
                    selectedCard = viewModel.cards.value?.find { it.id == cardId }
                    selectedTab = 1
                }
                1 -> {
                    selectedCard?.let { CardDetailScreen(it) }
                }
            }
        }
    }
}

