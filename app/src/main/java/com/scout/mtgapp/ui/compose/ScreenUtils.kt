package com.scout.mtgapp.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scout.mtgapp.R
import com.scout.mtgapp.ui.theme.BrightRed
import com.scout.mtgapp.ui.theme.MyBlue
import com.scout.mtgapp.ui.theme.MyPurple


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
        modifier = modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(
            listOf(
                MyPurple,
                MyBlue
            )
        ))
    )
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun MyBackground() {
    Image(
        painter = painterResource(id = R.drawable.img_background2),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun MyTopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
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
        var searchQuery = ""
        /*
        SearchBar(
            query = searchQuery,
            onQueryChanged = { newQuery -> searchQuery = newQuery },
            onSearch = {
                viewModel.searchCards(searchQuery) // Chama a função de busca no ViewModel
                //selectedTab = 0
            }
        )
        */
    }
}

@Composable
fun MyBottomBar(
    selectedTab: Int,
    onTabChange: (Int) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Home") },
            selected = selectedTab == 0,
            onClick = { onTabChange(0) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = null) },
            label = { Text("Search") },
            selected = selectedTab == 1,
            onClick = { onTabChange(1) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = null) },
            label = { Text("Saved Cards") },
            selected = selectedTab == 2,
            onClick = { onTabChange(2) }
        )
    }
}



/*
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

/*
@Composable
fun MainComponent(viewModel: CardViewModel) {

    val randomCard by viewModel.randomCard.observeAsState()
    val isCardInDatabase by viewModel.isCardInDatabase.observeAsState()

    var selectedTab by remember { mutableIntStateOf(1) }
    var selectedCard by remember { mutableStateOf<CardResponse?>(null) }
    var searchQuery by remember { mutableStateOf("") }

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
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    label = { Text("Details") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("Saved Cards") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                0 -> CardResponseListScreen(viewModel.cards) { cardId ->
                    viewModel.getCard(cardId)
                    selectedCard = viewModel.cards.value?.find { it.id == cardId }
                    selectedTab = 1
                }
                1 -> {
                    selectedCard?.let {
                        viewModel.checkCardInDatabase(it.id)
                        Log.d("COMPOSER", isCardInDatabase.toString())
                        if (isCardInDatabase == true) {
                            CardDetailScreen(it, "Remover Carta") { card ->
                                viewModel.deleteCard(card)
                            }
                        } else {
                            CardDetailScreen(it, "Salvar Carta") { card ->
                                viewModel.saveCard(card)
                            }
                        }
                    }
                }
                2 -> {
                    CardListScreen(viewModel.savedCards) { }
                }
            }
        }
    }
}
*/

