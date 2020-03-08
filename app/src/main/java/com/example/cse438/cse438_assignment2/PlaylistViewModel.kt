package com.example.cse438.cse438_assignment2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.database.Playlist
import com.example.cse438.cse438_assignment2.database.PlaylistRepository
import com.example.cse438.cse438_assignment2.database.PlaylistRoomDatabase


class PlaylistViewModel(application: Application): AndroidViewModel(application) {
    var _playlistList: LiveData<List<Playlist>> = MutableLiveData()
    private val repository: PlaylistRepository

    init {
        repository = PlaylistRepository(PlaylistRoomDatabase.getDatabase(application).playlistDao())
        _playlistList = repository.allPlaylists
    }

    fun getPlaylists() : LiveData<List<Playlist>>{
        return _playlistList
    }

    fun insert(playlist: Playlist) {
        repository.insert(playlist)
    }

    fun clear() {
        repository.clear()
    }



}