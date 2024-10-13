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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.scout.mtgapp.data.remote.entity.CardResponse

@Composable
fun CardDetailScreen(
    card: CardResponse,
    txtButton: String,
    onButtonClick: (CardResponse) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Exibindo a imagem da carta com Coil
        Image(
            painter = rememberAsyncImagePainter(card.imageUris?.small ?: ""),
            contentDescription = card.name,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(400.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = card.name, style = MaterialTheme.typography.titleLarge)
        Text(text = card.typeLine, style = MaterialTheme.typography.bodyMedium)
        Text(text = card.oracleText, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onButtonClick(card) }) {
            Text(text = txtButton)
        }
    }
}
