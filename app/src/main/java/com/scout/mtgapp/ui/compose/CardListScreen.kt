package com.scout.mtgapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.ui.theme.DarkSurface
import com.scout.mtgapp.ui.viewmodel.CardViewModel

@Composable
fun CardListScreen(viewModel: CardViewModel) {

    val uiState by viewModel.uiState.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()
    val cardList by viewModel.cardList.collectAsState()
    val query by viewModel.query.collectAsState()

    ScreenBase(
        selectedTab = selectedTab ?: 0,
        onTabChange = { newTab -> viewModel.selectTab(newTab) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (query.isNotEmpty()){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // Usa a SearchBar passando a query e as funções de mudança e busca
                    SearchBar(
                        query = query,
                        onQueryChanged = { viewModel.onQueryChanged(it) },
                        onSearch = { viewModel.searchCards() } // Chama a função de busca na API
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth() // Garante que a lista ocupe toda a largura
                            .weight(1f) // Ocupa o espaço restante abaixo da SearchBar
                    ) {
                        items(cardList) { card ->
                            CardItem(card = card, onClick = { viewModel.showCardDetail(card) })
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // Usa a SearchBar passando a query e as funções de mudança e busca
                    SearchBar(
                        query = query,
                        onQueryChanged = { viewModel.onQueryChanged(it) },
                        onSearch = { viewModel.searchCards() } // Chama a função de busca na API
                    )

                    Text(
                        "You can search any card by it's name, just try..."
                    )
                }
            }
        }
    }
}

@Composable
fun CardItem(card: CardResponse, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
            .clickable { onClick() }, // Adiciona o evento de clique,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Exibindo imagem da carta
            AsyncImage(
                model = card.imageUris?.normal, // Certifique-se de que 'imageUrl' está correto no CardResponse
                contentDescription = card.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f) // Mantém uma proporção da imagem
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Exibindo o nome da carta com estilo
            Text(
                text = card.name,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Exibindo detalhes adicionais da carta
            Text(
                text = "Tipo: ${card.typeLine}",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}