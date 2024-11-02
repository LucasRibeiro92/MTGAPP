package com.scout.mtgapp.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.scout.mtgapp.data.local.entity.card.Card
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.ui.viewmodel.CardViewModel

@Composable
fun SavedCardListScreen(viewModel: CardViewModel) {

    val uiState by viewModel.uiState.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()
    val savedCardList by viewModel.savedCardList.collectAsState()

    ScreenBase(
        selectedTab = selectedTab ?: 2,
        onTabChange = { newTab -> viewModel.selectTab(newTab) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth() // Garante que a lista ocupe toda a largura
            ) {
                items(savedCardList) { card ->
                    SavedCardItem(card = card, onClick = { /*viewModel.showCardDetail(card)*/ })
                }
            }
        }
    }
}

@Composable
fun SavedCardItem(card: Card, onClick: () -> Unit) {
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
                model = card.imageUri, // Certifique-se de que 'imageUrl' está correto no Card
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