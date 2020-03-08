package com.example.cse438.cse438_assignment2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cse438.cse438_assignment2.Data.FullTrack

@Entity(tableName = "Playlists")
data class Playlist (
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name="genre")
    val genre: String
)
