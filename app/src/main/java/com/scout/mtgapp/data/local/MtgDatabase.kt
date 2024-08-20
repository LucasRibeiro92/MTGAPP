package com.scout.mtgapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.scout.mtgapp.data.local.dao.CardDao
import com.scout.mtgapp.data.local.entity.card.Card
import com.scout.mtgapp.util.Constants.MTG_DATABASE

@Database(entities = [Card::class], version = 1)
abstract class MtgDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    companion object {
        @Volatile private var instance: MtgDatabase? = null

        fun getInstance(context: Context): MtgDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MtgDatabase::class.java, MTG_DATABASE)
                .fallbackToDestructiveMigration()
                .build()
    }
}