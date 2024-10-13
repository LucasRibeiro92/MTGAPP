package com.scout.mtgapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.scout.mtgapp.ui.compose.MainScreen
import com.scout.mtgapp.ui.theme.MTGAppTheme
import com.scout.mtgapp.ui.viewmodel.CardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<CardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MTGAppTheme {
                MainScreen(viewModel)
            }
        }
    }
}