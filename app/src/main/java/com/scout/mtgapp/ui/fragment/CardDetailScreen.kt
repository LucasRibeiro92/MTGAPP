package com.scout.mtgapp.ui.fragment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.scout.mtgapp.data.remote.entity.CardResponse

@Composable
fun CardDetailScreen(card: CardResponse) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Exibindo a imagem da carta com Coil
        Image(
            painter = rememberAsyncImagePainter(card.imageUris?.small ?: ""),
            contentDescription = card.name,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = card.name, style = MaterialTheme.typography.titleLarge)
        Text(text = card.typeLine, style = MaterialTheme.typography.bodyMedium)
        Text(text = card.oracleText, style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* Adicione a lógica de salvar aqui */ }) {
            Text(text = "Salvar Carta")
        }
    }
}