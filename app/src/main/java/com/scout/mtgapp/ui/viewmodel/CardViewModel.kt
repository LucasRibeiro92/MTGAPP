package com.scout.mtgapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scout.mtgapp.data.entity.card.Card
import com.scout.mtgapp.data.repository.CardRepository
import kotlinx.coroutines.launch

class CardViewModel(private val cardRepository: CardRepository) : ViewModel() {

    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> get() = _cards

    private val _card = MutableLiveData<Card>()
    val card: LiveData<Card> get() = _card

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

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

    fun loadRandomCards() {
        viewModelScope.launch {
            try {
                Log.d("CardViewModel", "Loading random cards")
                _cards.value = cardRepository.loadRandomCards()
            } catch (e: Exception) {
                Log.e("CardViewModel", "Error loading random cards", e)
                _error.value = e.message
            }
        }
    }

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
}