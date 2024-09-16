package com.scout.mtgapp.data.repository

import android.util.Log
import com.scout.mtgapp.data.local.dao.CardDao
import com.scout.mtgapp.data.local.entity.card.Card
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.data.remote.ScryfallApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardRepository(private val cardDao: CardDao) {
    private val apiService = ScryfallApiClient.apiService

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

    // Nova função para obter uma carta específica
    suspend fun getCard(id: String): CardResponse {
        val response = apiService.getCard(id)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Card not found")
        } else {
            throw Exception(response.message())
        }
    }

    suspend fun loadRandomCards(count: Int = 10): List<CardResponse> {
        val randomCards = mutableListOf<CardResponse>()
        repeat(count) {
            try {
                val response = apiService.getRandomCard()
                if (response.isSuccessful) {
                    response.body()?.let { randomCards.add(it) }
                    Log.d("CardRepository", response.body().toString())
                } else {
                    Log.e("CardRepository", "Error fetching random card: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("CardRepository", "Error fetching random card", e)
            }
        }
        return randomCards
    }

    suspend fun saveCard(card: Card) {
        withContext(Dispatchers.IO) {
            try {
                cardDao.insert(card)
            } catch (e: Exception) {
                throw e
            }
        }
    }
}