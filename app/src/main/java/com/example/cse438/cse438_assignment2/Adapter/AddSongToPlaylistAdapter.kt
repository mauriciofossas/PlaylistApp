package com.example.cse438.cse438_assignment2.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.ShowPlaylistsToAddSongActivity
import com.example.cse438.cse438_assignment2.SongsViewModel
import com.example.cse438.cse438_assignment2.TracksInAPlaylist
import com.example.cse438.cse438_assignment2.database.Playlist
import com.example.cse438.cse438_assignment2.database.Song
import com.example.cse438.cse438_assignment2.database.SongRepository
import com.example.cse438.cse438_assignment2.database.SongsDao

class AddSongViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.add_song_to_playlist_layout, parent, false)) {
    private val playlistName : TextView

    //To add the song to our database
    private var songViewModel: SongsViewModel? = null

    private val buttonToAddSong: Button = itemView.findViewById(R.id.addTrackToThisPlaylistButton)

    init {
        playlistName = itemView.findViewById(R.id.PlaylistToAddTo)
    }

    fun bind(playlist: Playlist, viewModel : SongsViewModel, sng : Song, myToast : Toast) {
        playlistName.text = playlist.name

        buttonToAddSong.setOnClickListener {
            val s = Song(sng.songTitle, sng.songArtist, sng.songDuration, playlist.name, "false")
            viewModel.insert(s)
            myToast.show()
        }

    }

}

//create the listener for the recycler view
class AddSongAdapter(private val list: ArrayList<Playlist>?, private val viewModel : SongsViewModel, private val sng : Song, private val myToast : Toast)
    : RecyclerView.Adapter<AddSongViewHolder>() {
    private var listEvents : ArrayList<Playlist>? = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddSongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AddSongViewHolder(inflater, parent)
    }

    //bind the object
    override fun onBindViewHolder(holder: AddSongViewHolder, position: Int) {
        val event: Playlist = listEvents!!.get(position)
        holder.bind(event, viewModel, sng, myToast)
    }

    //set the count
    override fun getItemCount(): Int = list!!.size

}