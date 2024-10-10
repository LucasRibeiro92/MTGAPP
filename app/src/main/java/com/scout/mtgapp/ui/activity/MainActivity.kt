package com.scout.mtgapp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.scout.mtgapp.R
import com.scout.mtgapp.ui.theme.MTGAppTheme
import com.scout.mtgapp.ui.viewmodel.CardViewModel
import org.koin.android.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<CardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadRandomCard()
        setContent {
            MTGAppTheme {
                MainScreen(viewModel)
            }
        }
    }
}