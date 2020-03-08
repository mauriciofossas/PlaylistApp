package com.example.cse438.cse438_assignment2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SongsTable")
data class Song (
    @ColumnInfo(name = "songTitle")
    val songTitle: String,
    @ColumnInfo(name = "songArtist")
    val songArtist: String,
    @ColumnInfo(name="songDuration")
    val songDuration: String,
    @ColumnInfo(name="playlistItBelongsTo")
    val playlistItBelongsTo: String,
    @ColumnInfo(name="LikedSongs")
    val songIsLiked: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}