package com.scout.mtgapp.data.local.entity.card

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.scout.mtgapp.util.Constants.CARD_TABLE

@Entity(tableName = CARD_TABLE)
data class Card(
    @PrimaryKey val id: String,
    val name: String,
    val imageUri: String?,
    val typeLine: String,
    val oracleText: String?,
    val power: String?,
    val toughness: String?,
    val setName: String,
    val rarity: String,
    val cmc: Double
)
