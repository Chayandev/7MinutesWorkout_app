package com.example.need7minute

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "workout_history")
data class HistoryData(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null, // Nullable Long to allow auto-generation
    val date:String,
    val time:String
)
