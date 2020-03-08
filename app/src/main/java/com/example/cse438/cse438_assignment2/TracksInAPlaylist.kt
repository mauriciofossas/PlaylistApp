package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.cse438_assignment2.Adapter.SongAdapter
import com.example.cse438.cse438_assignment2.database.Song
import kotlinx.android.synthetic.main.activity_tracks_in_a_playlist.*
import kotlinx.android.synthetic.main.fragment_playlists.*

class TracksInAPlaylist : AppCompatActivity() {

    private var songList: ArrayList<Song> = ArrayList()

    private lateinit var viewModel: SongsViewModel

    var playlistName = ""
    var playlistGenre = ""
    var playlistRating = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracks_in_a_playlist)

        showAndUpdateData()
    }

    override fun onStart() {
        super.onStart()

        showAndUpdateData()

    }

    fun showAndUpdateData(){
        playlistName = intent!!.getStringExtra("playlistName")
        playlistGenre = intent!!.getStringExtra("playlistGenre")
        playlistRating = intent!!.getDoubleExtra("playlistRating", 0.0).toString()

        PlaylistTitle.text = playlistName
        PlaylistInformation.text = "Genre: " + playlistGenre + ". Rating: " + playlistRating

        var adapter = SongAdapter(songList, ViewModelProvider(this).get(SongsViewModel::class.java))
        songsInPlaylist_recycler_view.adapter = adapter
        songsInPlaylist_recycler_view.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(SongsViewModel::class.java)

        //Will update our songList to hold songs only associated to the play list with that name.
        viewModel.getSongsForPlaylist(playlistName)

        viewModel._songList?.observe(this, Observer { songs ->
            // Update the cached copy of the words in the adapter.
            songList.clear()
            songList.addAll(songs)
            adapter.notifyDataSetChanged()
        })

        LeavePlaylist.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("tabToGoTo", 1)
            startActivity(intent)
        }
    }

}
