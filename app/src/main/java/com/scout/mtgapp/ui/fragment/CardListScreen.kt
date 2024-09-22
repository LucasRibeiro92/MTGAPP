package com.scout.mtgapp.ui.fragment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.ui.viewmodel.CardViewModel


@Composable
fun CardListScreen(cardList: List<CardResponse>, onCardClick: (String) -> Unit) {
    LazyColumn {
        items(cardList) { card ->
            // Aqui você exibe cada carta (personalize conforme sua necessidade)
            CardItem(card = card, onCardClick = onCardClick)
        }
    }
}

@Composable
fun CardItem(card: CardResponse, onCardClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp)),
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
