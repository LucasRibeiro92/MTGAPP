package com.scout.mtgapp.data.repository

import android.util.Log
import com.scout.mtgapp.data.entity.card.Card
import com.scout.mtgapp.data.remote.ScryfallApiClient

class CardRepository {
    private val apiService = ScryfallApiClient.apiService

    suspend fun searchCards(query: String): List<Card> {
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
    suspend fun getCard(id: String): Card {
        val response = apiService.getCard(id)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Card not found")
        } else {
            throw Exception(response.message())
        }
    }

    suspend fun loadRandomCards(count: Int = 10): List<Card> {
        val randomCards = mutableListOf<Card>()
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
}