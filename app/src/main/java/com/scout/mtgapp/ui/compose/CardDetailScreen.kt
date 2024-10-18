package com.scout.mtgapp.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.ui.viewmodel.CardViewModel

@Composable
fun CardDetailScreen(viewModel: CardViewModel) {

    val uiState by viewModel.uiState.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()
    val card by viewModel.card.collectAsState()
    val isInDB by viewModel.isInDB.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Exibindo a imagem da carta com Coil
        Image(
            painter = rememberAsyncImagePainter(card?.imageUris?.small ?: ""),
            contentDescription = card?.name,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(400.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        card?.let { Text(text = it.name, style = MaterialTheme.typography.titleLarge) }
        card?.let { Text(text = it.typeLine, style = MaterialTheme.typography.bodyMedium) }
        card?.let { Text(text = it.oracleText, style = MaterialTheme.typography.bodyMedium) }

        Spacer(modifier = Modifier.height(16.dp))

        if(isInDB) {
            Button(
                onClick = { card?.let { viewModel.deleteCard(it) } }
            ) {
                Text(text = "Remover")
            }
        } else {
            Button(
                onClick = { card?.let { viewModel.saveCard(it) } }
            ) {
                Text(text = "Adicionar")
            }
        }
    }
}
