package com.scout.mtgapp.di

import android.content.Context
import androidx.room.Room
import com.scout.mtgapp.data.local.MtgDatabase
import com.scout.mtgapp.util.Constants.MTG_DATABASE

fun provideDatabase(context: Context): MtgDatabase =
    Room.databaseBuilder(context, MtgDatabase::class.java, MTG_DATABASE)
        .fallbackToDestructiveMigration()
        .build()

fun provideCardDao(db: MtgDatabase) = db.cardDao()