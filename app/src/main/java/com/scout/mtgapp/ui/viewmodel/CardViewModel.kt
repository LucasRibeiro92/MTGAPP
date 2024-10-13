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

class CardViewModel(private val cardRepository: CardRepository) : ViewModel() {

    // Variable to store a list of cards
    private val _cards = MutableLiveData<List<CardResponse>>()
    val cards: LiveData<List<CardResponse>> get() = _cards

    // Variable to store a card
    private val _card = MutableStateFlow<CardResponse?>(null)
    val card: StateFlow<CardResponse?> = _card

    // Variable to store a random card
    private val _randomCard = MutableLiveData<CardResponse?>()
    val randomCard: LiveData<CardResponse?> get() = _randomCard

    // Variable to store errors during the process
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    // Flag to indicate if a card is in DB
    private val _isCardInDatabase = MutableLiveData<Boolean>()
    val isCardInDatabase: LiveData<Boolean> get() = _isCardInDatabase

    // LiveData to store bd saved cards
    val savedCards: LiveData<List<Card>> = cardRepository.getAllSavedCards()

    //Function to search card on Scryfall API resulting in a list of cards
    fun searchCards(query: String) {
        viewModelScope.launch {
            try {
                Log.d("CardViewModel", "Searching for cards with query: $query")
                _cards.value = cardRepository.searchCards(query)
                Log.d("CardViewModel", _cards.toString())
            } catch (e: Exception) {
                Log.e("CardViewModel", "Error searching cards", e)
                _error.value = e.message
            }
        }
    }

    //Function to search a random card on Scryfall API.
    fun loadRandomCard() {
        viewModelScope.launch {
            try {
                Log.d("CardViewModel", "Loading random card")
                _randomCard.value = cardRepository.loadRandomCards()
            } catch (e: Exception) {
                Log.e("CardViewModel", "Error loading random card", e)
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

    // Function to recover and indicate if a card is in DB
    fun checkCardInDatabase(id: String) {
        viewModelScope.launch {
            try {
                Log.d("CardViewModel", "Checking if card with id: $id is in DB")
                _isCardInDatabase.value = cardRepository.isCardInDatabase(id)
            } catch (e: Exception) {
                Log.e("CardViewModel", "Error checking card in DB", e)
                _isCardInDatabase.value = false
            }
        }
    }

    //Function to save a card into LocalBD.
    fun saveCard(cardResponse: CardResponse) {
        val cardEntity = cardResponse.toCard()
        viewModelScope.launch {
            cardRepository.saveCard(cardEntity)
        }
    }

    //Function to delete a card from LocalBD.
    fun deleteCard(card: CardResponse) {
        val cardEntity = card.toCard()
        viewModelScope.launch {
            cardRepository.deleteCard(cardEntity)
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
}