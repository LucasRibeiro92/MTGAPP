package com.scout.mtgapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scout.mtgapp.data.local.entity.card.Card
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.data.repository.CardRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull

sealed class CardState {
    data object Loading : CardState()
    data class Success(
        val selectedTab: Int? = 0,
        val card: CardResponse? = null, // Detalhe da carta (opcional)
        val isInDB: Boolean? = null,
        val cardList: List<CardResponse>? = null, // Lista de cartas (opcional)
    ) : CardState()
    data class Error(val message: String) : CardState()
}

class CardViewModel(private val cardRepository: CardRepository) : ViewModel() {

    // Controla o estado geral da UI (incluindo diferentes telas)
    private val _uiState = MutableStateFlow<CardState>(CardState.Loading)
    val uiState = _uiState.asStateFlow()

    // StateFlow to store tab
    private val _selectedTab = MutableStateFlow<Int?>(0)
    val selectedTab = _selectedTab.asStateFlow()

    // StateFlow to store a card
    private val _card = MutableStateFlow<CardResponse?>(null)
    val card = _card.asStateFlow()

    // StateFlow to indicate if a card is in DB
    private val _isInDB = MutableStateFlow<Boolean>(false)
    val isInDB = _isInDB.asStateFlow()

    // StateFlow to store a list of cards
    private val _cardList = MutableStateFlow<List<CardResponse>>(emptyList())
    val cardList = _cardList.asStateFlow()

    // StateFlow to store errors during the process
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    init {
        // Load a random card when the ViewModel is initialized
        loadRandomCard()
    }

    // Function to load a random card from Scryfall API
    private fun loadRandomCard() {
        viewModelScope.launch {
            delay(2000) // Simulando atraso
            try {
                _card.value = cardRepository.loadRandomCards()
                checkCardInDatabase(_card.value!!.id)
                _uiState.update { CardState.Success() }
            } catch (e: Exception) {
                _error.value = e.message
                _uiState.update { CardState.Error(e.message.toString()) }
            }
        }
    }

    // Function to search cards on Scryfall API resulting in a list of cards
    fun searchCards(query: String) {
        viewModelScope.launch {
            try {
                _cardList.value = cardRepository.searchCards(query)
            } catch (e: Exception) {
                _error.value = e.message
                _uiState.update { CardState.Error(e.message.toString()) }
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
                _uiState.update { CardState.Error(e.message.toString()) }
            }
        }
    }

    // Function to check if a card is in the database
    private fun checkCardInDatabase(id: String) {
        viewModelScope.launch {
            try {
                _isInDB.value = cardRepository.isCardInDatabase(id)
            } catch (e: Exception) {
                _isInDB.value = false
                _error.value = e.message
                _uiState.update { CardState.Error(e.message.toString()) }
            }
        }
    }

    // Function to save a card into the local DB
    fun saveCard(cardResponse: CardResponse) {
        viewModelScope.launch {
            cardRepository.saveCard(cardResponse.toCard())
            checkCardInDatabase(cardResponse.id)
            _uiState.update { CardState.Success() }
            //_uiState.emit(CardState.Success())
        }
    }

    // Function to delete a card from the local DB
    fun deleteCard(cardResponse: CardResponse) {
        viewModelScope.launch {
            cardRepository.deleteCard(cardResponse.toCard())
            checkCardInDatabase(cardResponse.id)
            _uiState.update { CardState.Success() }
            //_uiState.emit(CardState.Success())
        }
    }

    //Function to load saved cards from local DB
    private fun getSavedCards() {
        viewModelScope.launch {
            // Load saved cards from local DB on initialization
            //_cardList.value = cardRepository.getAllSavedCards()
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