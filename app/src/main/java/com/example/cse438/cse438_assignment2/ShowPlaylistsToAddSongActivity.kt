package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.cse438_assignment2.Adapter.AddSongAdapter
import com.example.cse438.cse438_assignment2.database.Playlist
import com.example.cse438.cse438_assignment2.database.Song
import kotlinx.android.synthetic.main.activity_show_playlists_to_add_song.*

class ShowPlaylistsToAddSongActivity : AppCompatActivity() {

    //To show playlists
    private var playlistsToAddTo: ArrayList<Playlist> = ArrayList()
    private lateinit var viewModel: PlaylistViewModel
    private lateinit var songsViewModel: SongsViewModel

    //To get the important information we need from our song to add to our playlist
    private var songToAdd = ""
    private var durationOfSong = ""
    private var artistOfSong = ""
    //To send back after the user has added the song to all desired playlists
    private var idToSendBack = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_playlists_to_add_song)

        //Getting data about our song to add to the playlists
        songToAdd = intent.getStringExtra("songToAdd")
        durationOfSong = intent.getStringExtra("durationOfSongToAdd")
        artistOfSong = intent.getStringExtra("artistOfSongToAdd")

        //Getting ID to send back
        idToSendBack = intent.getStringExtra("idOfSong")

        songsViewModel = ViewModelProvider(this).get(SongsViewModel::class.java)

        val s = Song(songToAdd, artistOfSong, durationOfSong, "", "false")

        val myToast = Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT)

        var adapter = AddSongAdapter(playlistsToAddTo, songsViewModel, s, myToast)

        playlists_to_add_songs_recycler_view.adapter = adapter
        playlists_to_add_songs_recycler_view.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)

        viewModel!!._playlistList.observe(this, Observer { playlists ->
            // Update the cached copy of the words in the adapter.
            playlistsToAddTo.clear()
            playlistsToAddTo.addAll(playlists)
            adapter.notifyDataSetChanged()
        })


        GoBackToDisplayedSong.setOnClickListener {
            val intent = Intent(this, SongActivity::class.java)
            intent.putExtra("id", idToSendBack)
            startActivity(intent)
        }
    }

}
