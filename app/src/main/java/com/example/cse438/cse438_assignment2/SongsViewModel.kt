package com.example.cse438.cse438_assignment2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.database.Song
import com.example.cse438.cse438_assignment2.database.SongRepository
import com.example.cse438.cse438_assignment2.database.SongRoomDatabase

class SongsViewModel(application: Application): AndroidViewModel(application) {
    var _songList: LiveData<List<Song>>? = MutableLiveData()

    var _likedSongList: LiveData<List<Song>>? = MutableLiveData()

    private val repository: SongRepository

    init {
        repository = SongRepository(SongRoomDatabase.getDatabase(application).songsDao())
    }

    fun getSongsForPlaylist(parentPlaylist : String) : LiveData<List<Song>>? {
        repository.getSongsForPlaylist(parentPlaylist)
        _songList = repository.allSongsBelongingToAPlaylist
        return _songList
    }

    fun insert(song: Song) {
        repository.insertSong(song)
    }

    fun clear(id : Int) {
        repository.deleteSong(id)
    }

    fun clearAll(songIsLiked : String){
        repository.clearAll(songIsLiked)
    }

    fun getLikedSongs(liked : String) : LiveData<List<Song>>?{
        repository.getAllLikedSongs(liked)
        _likedSongList = repository.allLikedSongs
        return _likedSongList
    }

}