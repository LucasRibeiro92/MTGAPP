package com.scout.mtgapp.ui.compose

import android.util.Log
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.ui.theme.BrightRed
import com.scout.mtgapp.ui.viewmodel.CardState
import com.scout.mtgapp.ui.viewmodel.CardViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(viewModel: CardViewModel = getViewModel<CardViewModel>()) {

    val uiState by viewModel.uiState.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()
    val erro by viewModel.error.collectAsState()

    when (uiState) {
        is CardState.Loading -> {
            LoadingScreen()
        }
        is CardState.Success -> {
            when (selectedTab) {
                0 -> {
                    CardDetailScreen(viewModel)
                }
                1 -> {
                    CardListScreen(viewModel)
                }
            }
        }
        is CardState.Error -> {
            // Exibe a tela de erro com a mensagem
            ErrorScreen(erro.toString())
        }
    }
}
