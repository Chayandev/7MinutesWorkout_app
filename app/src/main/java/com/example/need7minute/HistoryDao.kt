package com.example.need7minute

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Query("select * from workout_history order by date desc")
    fun getAllHistory():List<HistoryData>

    @Insert
    fun insert(data:HistoryData)

}