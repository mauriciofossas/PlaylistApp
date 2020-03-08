package com.example.cse438.cse438_assignment2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cse438.cse438_assignment2.Data.FullTrack


@Dao
interface PlaylistDao {

    //Gets all songs from a specific playlist
    //@Query("SELECT songs FROM Playlists WHERE name = :playlistname")
    //fun getTracks(playlistname : String): LiveData<List<FullTrack>>

    //Gets all playlists
    @Query("SELECT * FROM Playlists")
    fun getPlaylists(): LiveData<List<Playlist>>

    //Inserts a new playlist into playlists
    @Insert
    fun insert(playlist: Playlist)

    //Deletes all playlists
    @Query("DELETE FROM Playlists")
    fun deleteAll()
}