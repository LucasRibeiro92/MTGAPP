package com.scout.mtgapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.scout.mtgapp.data.local.entity.card.Card
import com.scout.mtgapp.util.Constants.CARD_TABLE

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(card: Card)

    @Delete
    fun delete(card: Card)

    //@Update
    //fun update(card: Card)

    @Query("SELECT * FROM $CARD_TABLE")
    fun getAllCards(): List<Card>

    @Query("SELECT * FROM $CARD_TABLE WHERE id = :cardId")
    fun getCardById(cardId: String): Card?

    @Query("SELECT COUNT(*) > 0 FROM $CARD_TABLE WHERE id = :cardId")
    fun cardExists(cardId: String): Boolean


    /*
        Complex examples:

            @Query("SELECT * FROM $CARD_TABLE WHERE id = :eventId")
            fun getEventById(eventId: String): Card?

            @Query("SELECT * FROM $CARD_TABLE WHERE isPendingSync = 1")
            fun getPendingSyncEvents(): List<Card>

    */
}

