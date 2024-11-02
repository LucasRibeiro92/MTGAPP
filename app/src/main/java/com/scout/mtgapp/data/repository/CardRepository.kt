package com.scout.mtgapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.scout.mtgapp.data.local.dao.CardDao
import com.scout.mtgapp.data.local.entity.card.Card
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.data.remote.ScryfallApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardRepository(private val cardDao: CardDao) {
    private val apiService = ScryfallApiClient.apiService

    //Search Cards by Name
    suspend fun searchCards(query: String): List<CardResponse> {
        return try {
            val response = apiService.searchCards(query)
            Log.d("CardRepository", "API response: $response")
            if (response.isSuccessful) {
                response.body()?.data ?: emptyList()
            } else {
                throw Exception("API error: ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("CardRepository", "Error fetching cards", e)
            throw e
        }
    }

    // Get a Card by ID
    suspend fun getCard(id: String): CardResponse {
        val response = apiService.getCard(id)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Card not found")
        } else {
            throw Exception(response.message())
        }
    }

    // Get a random Card
    suspend fun loadRandomCards(): CardResponse {
        val response = apiService.getRandomCard()
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Error fetching random card")
        } else {
            throw Exception(response.message())
        }
    }

    // Save a Card into Room
    suspend fun saveCard(card: Card) {
        withContext(Dispatchers.IO) {
            try {
                cardDao.insert(card)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    // Delete a Card from Room
    suspend fun deleteCard(card: Card) {
        withContext(Dispatchers.IO) {
            try {
                cardDao.delete(card)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    // Função para buscar todas as cartas salvas
    suspend fun getAllSavedCards(): List<Card> {
        return withContext(Dispatchers.IO) {
            cardDao.getAllCards()
        }
    }

    // Função para buscar uma carta pelo ID
    fun getCardById(cardId: String): Card? {
        return cardDao.getCardById(cardId)
    }

    // Função para verificar se o card está no banco de dados
    suspend fun isCardInDatabase(id: String): Boolean {
        return withContext(Dispatchers.IO) {
            cardDao.cardExists(id)  // Executa no contexto de I/O
        }
    }
}