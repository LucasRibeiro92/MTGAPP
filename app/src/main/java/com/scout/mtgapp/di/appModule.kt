package com.scout.mtgapp.di

import com.scout.mtgapp.data.repository.CardRepository
import com.scout.mtgapp.ui.viewmodel.CardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // ViewModels
    viewModel { CardViewModel(get()) }

    // Repository
    single { CardRepository() }
}