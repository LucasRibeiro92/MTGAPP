package com.scout.mtgapp.data.remote

import com.scout.mtgapp.data.entity.card.Card
import com.scout.mtgapp.data.entity.card.CardSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScryfallApiService {
    @GET("cards/search")
    suspend fun searchCards(@Query("q") query: String): Response<CardSearchResponse>

    @GET("cards/{id}")
    suspend fun getCard(@Path("id") id: String): Response<Card>

    @GET("cards/random")
    suspend fun getRandomCard(): Response<Card>
}