package com.scout.mtgapp.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import coil.compose.AsyncImage
import com.scout.mtgapp.data.local.entity.card.Card
import com.scout.mtgapp.data.remote.entity.CardResponse

@Composable
fun CardResponseListScreen(cardList: LiveData<List<CardResponse>>, onCardClick: (String) -> Unit) {

    val cards by cardList.observeAsState(initial = emptyList()) // Observa o LiveData e atualiza a UI

    LazyColumn {
        items(cards) { card ->
            // Exibe cada carta
            CardResponseItem(card = card, onCardClick = onCardClick)
        }
    }
}

@Composable
fun CardResponseItem(card: CardResponse, onCardClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
            .clickable { onCardClick(card.id) }, // Adiciona o evento de clique,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Exibindo imagem da carta
            AsyncImage(
                model = card.imageUris?.small, // Certifique-se de que 'imageUrl' está correto no CardResponse
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
