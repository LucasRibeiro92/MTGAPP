package com.scout.mtgapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scout.mtgapp.data.local.entity.card.Card
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.data.repository.CardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CardState {
    data object Loading : CardState()
    data class Success(val card: CardResponse, val selectedTab: Int) : CardState()
    data class Error(val message: String) : CardState()
}

class CardViewModel(private val cardRepository: CardRepository) : ViewModel() {

    // Controla o estado geral da UI (incluindo diferentes telas)
    private val _uiState = MutableStateFlow<CardState>(CardState.Loading)
    val uiState: StateFlow<CardState> get() = _uiState

    // StateFlow to store the list of saved cards from local DB
    private val _savedCards = MutableStateFlow<List<Card>>(emptyList())
    val savedCards: StateFlow<List<Card>> get() = _savedCards

    // StateFlow to store a list of cards
    private val _cards = MutableStateFlow<List<CardResponse>>(emptyList())
    val cards: StateFlow<List<CardResponse>> get() = _cards

    // StateFlow to store a card
    private val _card = MutableStateFlow<CardResponse?>(null)
    val card: StateFlow<CardResponse?> get() = _card

    // StateFlow to store a random card
    private val _randomCard = MutableStateFlow<CardResponse?>(null)
    val randomCard: StateFlow<CardResponse?> get() = _randomCard

    // StateFlow to store errors during the process
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    // StateFlow to indicate if a card is in DB
    private val _isCardInDatabase = MutableStateFlow<Boolean>(false)
    val isCardInDatabase: StateFlow<Boolean> get() = _isCardInDatabase

    init {
        // Load a random card when the ViewModel is initialized
        loadRandomCard()
        //Function to load saved cards from local DB
        getSavedCards()
    }

    // Function to search cards on Scryfall API resulting in a list of cards
    fun searchCards(query: String) {
        viewModelScope.launch {
            try {
                _cards.value = cardRepository.searchCards(query)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    // Function to load a random card from Scryfall API
    fun loadRandomCard() {
        viewModelScope.launch {
            try {
                _randomCard.value = cardRepository.loadRandomCards()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    // Function to get a card from Scryfall API.
    fun getCard(id: String) {
        viewModelScope.launch {
            try {
                Log.d("CardViewModel", "Fetching card with id: $id")
                _card.value = cardRepository.getCard(id)
            } catch (e: Exception) {
                Log.e("CardViewModel", "Error fetching card", e)
                _error.value = e.message
            }
        }
    }

    // Function to check if a card is in the database
    fun checkCardInDatabase(id: String) {
        viewModelScope.launch {
            try {
                _isCardInDatabase.value = cardRepository.isCardInDatabase(id)
            } catch (e: Exception) {
                _isCardInDatabase.value = false
                _error.value = e.message
            }
        }
    }

    // Function to save a card into the local DB
    fun saveCard(cardResponse: CardResponse) {
        viewModelScope.launch {
            cardRepository.saveCard(cardResponse.toCard())
        }
    }

    // Function to delete a card from the local DB
    fun deleteCard(cardResponse: CardResponse) {
        viewModelScope.launch {
            cardRepository.deleteCard(cardResponse.toCard())
        }
    }

    //Function to load saved cards from local DB
    private fun getSavedCards() {
        viewModelScope.launch {
            // Load saved cards from local DB on initialization
            _savedCards.value = cardRepository.getAllSavedCards()
        }
    }

    //CardResponse into CardEntity
    private fun CardResponse.toCard(): Card {
        return Card(
            id = this.id,
            name = this.name,
            imageUri = this.imageUris?.large ?: "",
            typeLine = this.typeLine,
            oracleText = this.oracleText,
            power = this.power,
            toughness = this.toughness,
            setName = this.setName,
            rarity = this.rarity,
            cmc = this.cmc
        )
    }

    // Função para alternar entre tabs
    fun selectTab(tab: Int) {
        val currentState = _uiState.value
        if (currentState is CardState.Success) {
            _uiState.value = currentState.copy(selectedTab = tab)
        }
    }
}