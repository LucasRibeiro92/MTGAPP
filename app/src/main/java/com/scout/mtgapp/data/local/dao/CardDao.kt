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
    @Query("SELECT * FROM $CARD_TABLE")
    fun getAllEvents(): LiveData<List<Card>>

    /*@Query("SELECT * FROM $CARD_TABLE WHERE id = :eventId")
    fun getEventById(eventId: String): Card?

    @Query("SELECT * FROM $CARD_TABLE WHERE isPendingSync = 1")
    fun getPendingSyncEvents(): List<Card>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: Card)

    @Delete
    fun delete(event: Card)

    @Update
    fun update(event: Card)
}