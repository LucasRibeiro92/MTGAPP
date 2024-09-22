package com.scout.mtgapp.ui.fragment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.scout.mtgapp.ui.viewmodel.CardViewModel

@Composable
fun CardListScreen(viewModel: CardViewModel) {
    // Usar o viewModel para acessar os dados e exibir a lista
    val cards = viewModel.cards.value ?: emptyList()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(cards.size) { index ->
            val card = cards[index]
            Text(text = card.name)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
