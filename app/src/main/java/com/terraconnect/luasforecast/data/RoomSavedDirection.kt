package com.terraconnect.luasforecast.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "luas_statuss_table")
data class RoomSavedDirection(@PrimaryKey @ColumnInfo(name = "destination")val destination: String, @ColumnInfo(name = "dueMins") val dueMins: String)