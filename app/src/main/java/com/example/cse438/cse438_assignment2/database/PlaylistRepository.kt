package com.example.cse438.cse438_assignment2.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PlaylistRepository(private val playlistDao : PlaylistDao) {
    val allPlaylists: LiveData<List<Playlist>> = playlistDao.getPlaylists()

    fun insert(playlist: Playlist) {
        CoroutineScope(Dispatchers.IO).launch {
            playlistDao.insert(playlist)
        }
    }

    fun clear() {
        CoroutineScope(Dispatchers.IO).launch {
            playlistDao.deleteAll()
        }
    }

}