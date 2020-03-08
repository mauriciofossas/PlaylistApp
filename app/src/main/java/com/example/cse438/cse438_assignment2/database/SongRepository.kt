package com.example.cse438.cse438_assignment2.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SongRepository(private val songsDao : SongsDao) {

    var allSongsBelongingToAPlaylist: LiveData<List<Song>>? = null

    var allLikedSongs: LiveData<List<Song>>? = null

    fun insertSong(song: Song) {
        CoroutineScope(Dispatchers.IO).launch {
            songsDao.insertSong(song)
        }
    }

    fun deleteSong(id : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            songsDao.deleteSong(id)
        }
    }

    fun getSongsForPlaylist(parentPlaylist : String)  {
        CoroutineScope(Dispatchers.IO).launch {
            allSongsBelongingToAPlaylist = songsDao.getSongsForPlaylist(parentPlaylist)
        }

    }

    fun clearAll(songIsLiked : String)  {
        CoroutineScope(Dispatchers.IO).launch {
            songsDao.deleteAll(songIsLiked)
        }

    }

    fun getAllLikedSongs(songIsLiked : String){
        CoroutineScope(Dispatchers.IO).launch {
            allLikedSongs = songsDao.getAllLiked(songIsLiked)
        }
    }

}