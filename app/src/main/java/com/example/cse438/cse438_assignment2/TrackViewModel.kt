package com.example.cse438.cse438_assignment2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.Data.ChartPayload
import com.example.cse438.cse438_assignment2.Data.FullTrack
import com.example.cse438.cse438_assignment2.Data.Payload
import com.example.cse438.cse438_assignment2.Network.TrackRepo

class TrackViewModel(application: Application): AndroidViewModel(application) {
    public var songList: MutableLiveData<ChartPayload> = MutableLiveData()
    public var trackRepository: TrackRepo = TrackRepo()
    public var tempSongList: MutableLiveData<Payload> = MutableLiveData()

    public var fullSong: MutableLiveData<FullTrack> = MutableLiveData()

    fun getTracks(filter: String){
        //This saves the result from getting our songs into the songList item (of mutable live data)
        trackRepository.getSongs(tempSongList, filter)
    }

    fun getTopTracks(){
        trackRepository.getTopSongs(songList)
    }

    fun getFullTrack(id: String){
        trackRepository.getFullSong(fullSong, id)
    }
}
