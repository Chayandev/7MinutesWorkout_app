package com.example.need7minute

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryData::class], version = 1)
abstract class HistoryDatabase:RoomDatabase() {
    // Declare an abstract function to get the DAO
    abstract fun historyDao(): HistoryDao

}