package com.example.cse438.cse438_assignment2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SongsDao {

    //Gets all songs from specified playlist
    @Query("SELECT * FROM SongsTable WHERE playlistItBelongsTo= :parentPlaylist")
    fun getSongsForPlaylist(parentPlaylist : String): LiveData<List<Song>>

    //Inserts a new song (which will come with the name of what playlist it belongs to)
    @Insert
    fun insertSong(song: Song)

    //Delete a song (which will delete it from the playlist)
    @Query("DELETE FROM SongsTable WHERE id = :songID")
    fun deleteSong(songID : Int)

    @Query("DELETE FROM SongsTable WHERE LikedSongs= :liked")
    fun deleteAll(liked : String)

    @Query("SELECT * FROM SongsTable WHERE LikedSongs= :liked")
    fun getAllLiked(liked : String) : LiveData<List<Song>>
}
