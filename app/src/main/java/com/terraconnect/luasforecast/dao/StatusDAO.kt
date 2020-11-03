package com.terraconnect.luasforecast.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.terraconnect.luasforecast.data.RoomSavedDirection

@Dao
interface StatusDAO {

    @Query("SELECT * from luas_statuss_table ORDER BY dueMins ASC")
    fun getStatusHistory(): LiveData<List<RoomSavedDirection>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(roomSavedDirection: RoomSavedDirection)

    @Query("DELETE FROM luas_statuss_table")
    fun deleteAll()
}

